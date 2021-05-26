package com.cafe.repository;

import com.cafe.model.entity.product.ProductMedia;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductMediaRepository extends CommonRepository<ProductMedia> {

    @Query("DELETE FROM ProductMedia pm WHERE pm.deleted = FALSE AND pm.product.id = :productId AND pm.isCoverImage = TRUE")
    @Transactional
    @Modifying
    void deleteAllByCoverImageTrueAndProductId(@Param("productId") Long productId);

}