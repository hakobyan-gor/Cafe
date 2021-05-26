package com.cafe.facade.order;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.exceptions.MessageException;
import com.cafe.model.dto.order.OrderCreatingDto;

import com.cafe.model.dto.order.OrderDetailUpdateDto;
import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderFacadeBuilder {

    @Lazy
    private final OrderCreatingDtoFacade mCreatingDtoFacade;
    @Lazy
    private final OrderUpdatingDtoFacade mUpdatingDtoFacade;

    public OrderFacadeBuilder(
            OrderCreatingDtoFacade orderCreatingDtoFacade,
            OrderUpdatingDtoFacade orderUpdatingDtoFacade
    ) {
        this.mCreatingDtoFacade = orderCreatingDtoFacade;
        this.mUpdatingDtoFacade = orderUpdatingDtoFacade;
    }

    public Long createOrder(OrderCreatingDto orderCreatingDto, Authentication authentication) throws Exception {
        return mCreatingDtoFacade.createOrder(orderCreatingDto, authentication);
    }

    public Boolean cancelOrder(Long orderId, Authentication authentication) throws Exception {
        return mUpdatingDtoFacade.cancelOrder(orderId, authentication);
    }

    public Boolean finishOrder(Long orderId, Authentication authentication) throws Exception {
        return mUpdatingDtoFacade.finishOrder(orderId, authentication);
    }

    public Boolean removeProductsFromOrder(Long orderId, List<Long> productIdList, Authentication authentication) throws Exception {
        return mUpdatingDtoFacade.removeProductsFromOrder(orderId, productIdList, authentication);
    }

    public Object addProductsToOrder(Long orderId, OrderDetailUpdateDto orderDetailUpdateDto, Authentication authentication) throws Exception {
        return mUpdatingDtoFacade.addProductsToOrder(orderId, orderDetailUpdateDto, authentication);
    }

}