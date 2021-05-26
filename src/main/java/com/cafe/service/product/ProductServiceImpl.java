package com.cafe.service.product;

import com.cafe.enums.ProductStatus;
import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.entity.product.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductRepository> implements ProductService {

    private final ProductRepository mRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.mRepository = productRepository;
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> productIdList) throws EntityNotFoundException {
        List<Product> productList = new ArrayList<>();
        for (Long id : productIdList) {
            Optional<Product> optionalProduct = mRepository.findByIdAndDeletedFalseAAndProductStatus(ProductStatus.AVAILABLE.getInt());
            if (optionalProduct.isEmpty())
                throw new EntityNotFoundException(Product.class, "Id", String.valueOf(id));

            productList.add(optionalProduct.get());
        }
        return productList;
    }

}
