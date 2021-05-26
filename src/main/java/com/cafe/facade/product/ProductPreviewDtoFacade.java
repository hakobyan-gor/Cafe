package com.cafe.facade.product;

import com.cafe.convert.ProductConverter;
import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.dto.product.ProductPreviewDto;
import com.cafe.model.dto.product.ProductPreviewDtoList;
import com.cafe.model.entity.product.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.utils.FindProduct;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ProductPreviewDtoFacade {

    @Lazy
    private final ProductRepository mRepository;
    @Lazy
    private final FindProduct findProduct;

    public ProductPreviewDtoFacade(
            ProductRepository productRepository,
            FindProduct findProduct
    ) {
        this.mRepository = productRepository;
        this.findProduct = findProduct;
    }

    public Page<ProductPreviewDtoList> getAllProducts(Pageable pageable, String languageName) {
        Page<Product> productPage = mRepository.findAll(pageable);
        return ProductConverter.convertToPreviewDtoPage(productPage, languageName);
    }

    public ProductPreviewDto getProductDetails(Long productId, String languageName) throws EntityNotFoundException {
        Product product = findProduct.findById(productId);
        return ProductConverter.convertToPreviewDto(product, languageName);
    }

}