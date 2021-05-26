package com.cafe.utils;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.entity.order.OrderEntity;
import com.cafe.repository.OrderRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindOrder {

    @Lazy
    private final OrderRepository mRepository;

    public FindOrder(OrderRepository orderRepository) {
        this.mRepository = orderRepository;
    }

    public OrderEntity findById(Long orderId) throws EntityNotFoundException {
        Optional<OrderEntity> optionalOrderEntity = mRepository.findById(orderId);

        if (optionalOrderEntity.isEmpty())
            throw new EntityNotFoundException(OrderEntity.class, "Id", String.valueOf(orderId));

        return optionalOrderEntity.get();
    }

}