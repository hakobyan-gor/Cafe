package com.cafe.repository;

import com.cafe.model.entity.order.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CommonRepository<OrderEntity> {
}