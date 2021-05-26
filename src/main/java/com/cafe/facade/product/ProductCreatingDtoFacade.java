package com.cafe.facade.product;

import com.cafe.convert.ProductConverter;
import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.exceptions.MessageException;
import com.cafe.model.dto.product.ProductCreatingDto;
import com.cafe.model.entity.product.Product;
import com.cafe.model.entity.product.ProductMedia;
import com.cafe.repository.ProductMediaRepository;
import com.cafe.repository.ProductRepository;
import com.cafe.utils.Configuration;
import com.cafe.utils.FileUtil;
import com.cafe.utils.FindProduct;
import com.cafe.utils.LanguageCheckUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductCreatingDtoFacade {

    @Lazy
    private final ProductMediaRepository productMediaRepository;
    @Lazy
    private final LanguageCheckUtil mLanguageCheckUtil;
    @Lazy
    private final ProductRepository mRepository;
    @Lazy
    private final FindProduct findProduct;

    public ProductCreatingDtoFacade(
            ProductMediaRepository productMediaRepository,
            LanguageCheckUtil languageCheckUtil,
            ProductRepository productRepository,
            FindProduct findProduct
    ) {
        this.productMediaRepository = productMediaRepository;
        this.mLanguageCheckUtil = languageCheckUtil;
        this.mRepository = productRepository;
        this.findProduct = findProduct;
    }

    public Long createProduct(ProductCreatingDto productCreatingDto) throws MessageException {
        mLanguageCheckUtil.checkLanguageName(productCreatingDto.getTranslateList().stream()
                .map(ProductCreatingDto.ProductTranslateCreatingDto::getLanguageName).collect(Collectors.toList()));
        Product product = ProductConverter.convertToEntity(productCreatingDto);
        product.getProductTranslateList().forEach(productTranslate -> productTranslate.setProduct(product));

        return mRepository.save(product).getId();
    }

    public Boolean uploadProductMedia(Long productId, List<MultipartFile> photos, MultipartFile coverPhoto) throws EntityNotFoundException {
        Product product = findProduct.findById(productId);

        List<ProductMedia> productMediaList = new ArrayList<>();
        if (photos != null && photos.size() > 0) {
            for (MultipartFile file : photos) {
                ProductMedia productMedia = new ProductMedia();
                productMedia.setProduct(product);
                String filesString = FileUtil.saveFile(Configuration.PRODUCT_IMAGE_DIR_IN_PROJECT, file);
                productMedia.setMediaUrl(filesString);
                productMedia.setCoverImage(false);
                productMediaList.add(productMedia);
            }
        }
        if (coverPhoto != null) {
            productMediaRepository.deleteAllByCoverImageTrueAndProductId(productId);
            ProductMedia collectionMedia = new ProductMedia();
            String filesString = FileUtil.saveFile(Configuration.PRODUCT_IMAGE_DIR_IN_PROJECT, coverPhoto);
            collectionMedia.setMediaUrl(filesString);
            collectionMedia.setCoverImage(true);
            collectionMedia.setProduct(product);
            productMediaList.add(collectionMedia);
        }

        product.setProductMediaList(productMediaList);
        return true;
    }

}
