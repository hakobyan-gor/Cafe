package com.cafe.facade.product;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.exceptions.MessageException;
import com.cafe.model.dto.product.ProductCreatingDto;
import com.cafe.model.dto.product.ProductPreviewDto;
import com.cafe.model.dto.product.ProductPreviewDtoList;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProductFacadeBuilder {

    @Lazy
    private final ProductCreatingDtoFacade mCreatingDtoFacade;
    @Lazy
    private final ProductPreviewDtoFacade mPreviewDtoFacade;

    public ProductFacadeBuilder(
            ProductCreatingDtoFacade productCreatingDtoFacade,
            ProductPreviewDtoFacade productPreviewDtoFacade
    ) {
        this.mCreatingDtoFacade = productCreatingDtoFacade;
        this.mPreviewDtoFacade = productPreviewDtoFacade;
    }

    public Long createProduct(ProductCreatingDto productCreatingDto) throws MessageException {
        return mCreatingDtoFacade.createProduct(productCreatingDto);
    }

    public Boolean uploadProductMedia(Long productId, List<MultipartFile> photos, MultipartFile coverPhoto) throws EntityNotFoundException {
        return mCreatingDtoFacade.uploadProductMedia(productId, photos, coverPhoto);
    }

    public Page<ProductPreviewDtoList> getAllProducts(Pageable pageable, String languageName) {
        return mPreviewDtoFacade.getAllProducts(pageable, languageName);
    }

    public ProductPreviewDto getProductDetails(Long productId, String languageName) throws EntityNotFoundException {
        return mPreviewDtoFacade.getProductDetails(productId, languageName);
    }

}