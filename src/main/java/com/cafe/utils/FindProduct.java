package com.cafe.utils;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.entity.product.Product;
import com.cafe.repository.ProductRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindProduct {

    @Lazy
    private final ProductRepository mRepository;

    public FindProduct(ProductRepository productRepository) {
        this.mRepository = productRepository;
    }

    public Product findById(Long productId) throws EntityNotFoundException {
        Optional<Product> optionalProduct = mRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new EntityNotFoundException(Product.class, "Id", String.valueOf(productId));

        return optionalProduct.get();
    }

}