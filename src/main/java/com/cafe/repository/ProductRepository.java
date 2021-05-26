package com.cafe.repository;

import com.cafe.model.entity.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CommonRepository<Product> {

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.productStatus = :productStatus")
    Optional<Product> findByIdAndDeletedFalseAAndProductStatus(@Param("productStatus") int productStatus);

}