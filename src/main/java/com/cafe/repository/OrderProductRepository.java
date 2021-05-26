package com.cafe.repository;

import com.cafe.model.entity.order.OrderProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderProductRepository extends CommonRepository<OrderProduct> {

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderProduct op WHERE op.orderId = :orderId AND op.productId IN :productIdList")
    void deleteAllByOrderIdAndProductIdIn(@Param("orderId")Long orderId, @Param("productIdList") List<Long> productIdList);

}