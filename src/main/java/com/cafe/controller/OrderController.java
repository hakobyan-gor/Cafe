package com.cafe.controller;

import com.cafe.facade.order.OrderFacadeBuilder;
import com.cafe.model.ResponseModel;
import com.cafe.model.dto.order.OrderCreatingDto;
import com.cafe.model.dto.order.OrderDetailUpdateDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController {

    private final OrderFacadeBuilder mFacadeBuilder;

    public OrderController(OrderFacadeBuilder orderFacadeBuilder) {
        this.mFacadeBuilder = orderFacadeBuilder;
    }

    @PreAuthorize("hasRole('WAITER')")
    @PostMapping
    public ResponseModel createOrder(@Valid @RequestBody OrderCreatingDto orderCreatingDto, Authentication authentication) {
        try {
            return createSuccessResult(mFacadeBuilder.createOrder(orderCreatingDto, authentication), "Order created successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('WAITER')")
    @PutMapping("removeProductsFromOrder/{orderId}")
    public ResponseModel removeProductsFromOrder(@PathVariable Long orderId,
                                                 @RequestBody List<Long> productIdList,
                                                 Authentication authentication) {
        try {
            return createSuccessResult(mFacadeBuilder.removeProductsFromOrder(orderId, productIdList, authentication), "Order details updated successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('WAITER')")
    @PutMapping("addProductsToOrder/{orderId}")
    public ResponseModel addProductsToOrder(@PathVariable Long orderId,
                                            @Valid @RequestBody OrderDetailUpdateDto orderDetailUpdateDto,
                                            Authentication authentication) {
        try {
            return createSuccessResult(mFacadeBuilder.addProductsToOrder(orderId, orderDetailUpdateDto, authentication), "Order details updated successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('WAITER')")
    @PutMapping("cancelOrder/{orderId}")
    public ResponseModel cancelOrder(@PathVariable Long orderId, Authentication authentication) {
        try {
            return createSuccessResult(mFacadeBuilder.cancelOrder(orderId, authentication), "Order canceled successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('WAITER')")
    @PutMapping("finishOrder/{orderId}")
    public ResponseModel finishOrder(@PathVariable Long orderId, Authentication authentication) {
        try {
            return createSuccessResult(mFacadeBuilder.finishOrder(orderId, authentication), "Order finished successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }


}