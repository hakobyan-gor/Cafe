package com.cafe.facade.order;

import com.cafe.enums.OrderStatus;
import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.exceptions.MessageException;
import com.cafe.model.dto.order.OrderDetailUpdateDto;
import com.cafe.model.entity.order.OrderEntity;
import com.cafe.model.entity.order.OrderProduct;
import com.cafe.model.entity.product.Product;
import com.cafe.model.entity.user.Waiter;
import com.cafe.repository.OrderProductRepository;
import com.cafe.repository.ProductRepository;
import com.cafe.utils.FindOrder;
import com.cafe.utils.FindWaiter;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderUpdatingDtoFacade {

    @Lazy
    private final OrderProductRepository mOrderProductRepository;
    @Lazy
    private final ProductRepository mProductRepository;
    @Lazy
    private final FindWaiter mWindWaiter;
    @Lazy
    private final FindOrder mFindOrder;

    public OrderUpdatingDtoFacade(
            OrderProductRepository orderProductRepository,
            ProductRepository productRepository,
            FindWaiter findWaiter,
            FindOrder findOrder
    ) {
        this.mOrderProductRepository = orderProductRepository;
        this.mProductRepository = productRepository;
        this.mWindWaiter = findWaiter;
        this.mFindOrder = findOrder;
    }

    public Boolean cancelOrder(Long orderId, Authentication authentication) throws Exception {
        Waiter waiter = mWindWaiter.findByToken(authentication);
        OrderEntity orderEntity = mFindOrder.findById(orderId);

        checkOrder(waiter, orderEntity);

        if (orderEntity.getOrderStatus().equals(OrderStatus.FINISHED) || orderEntity.getOrderStatus().equals(OrderStatus.CANCELED))
            throw new MessageException("Yuo cannot cancel finished or canceled order.");

        orderEntity.setOrderCanceledDate(System.currentTimeMillis());
        orderEntity.setOrderStatus(OrderStatus.CANCELED);

        return true;
    }

    public Boolean finishOrder(Long orderId, Authentication authentication) throws Exception {
        Waiter waiter = mWindWaiter.findByToken(authentication);
        OrderEntity orderEntity = mFindOrder.findById(orderId);

        checkOrder(waiter, orderEntity);

        if (orderEntity.getOrderStatus().equals(OrderStatus.FINISHED) || orderEntity.getOrderStatus().equals(OrderStatus.CANCELED))
            throw new MessageException("Yuo cannot cancel finished or canceled order.");

        orderEntity.setOrderCanceledDate(System.currentTimeMillis());
        orderEntity.setOrderStatus(OrderStatus.FINISHED);

        return true;
    }

    public Boolean removeProductsFromOrder(Long orderId, List<Long> productIdList, Authentication authentication) throws Exception {
        Waiter waiter = mWindWaiter.findByToken(authentication);
        OrderEntity orderEntity = mFindOrder.findById(orderId);

        checkOrder(waiter, orderEntity);

        mOrderProductRepository.deleteAllByOrderIdAndProductIdIn(orderId, productIdList);
        return true;
    }

    public Object addProductsToOrder(Long orderId, OrderDetailUpdateDto orderDetailUpdateDto, Authentication authentication) throws Exception {
        Waiter waiter = mWindWaiter.findByToken(authentication);
        OrderEntity orderEntity = mFindOrder.findById(orderId);

        checkOrder(waiter, orderEntity);

        if (!orderEntity.getWaiter().equals(waiter))
            throw new MessageException("Order with given id not found.");

        for (OrderDetailUpdateDto.OrderDetail orderDetail : orderDetailUpdateDto.getOrderDetailList()) {
            Product product = mProductRepository.getById(orderDetail.getProductId());
            OrderProduct orderProduct = OrderProduct
                    .builder()
                    .productId(orderDetail.getProductId())
                    .quantity(orderDetail.getQuantity())
                    .orderId(orderEntity.getId())
                    .price(product.getPrice())
                    .build();
            mOrderProductRepository.save(orderProduct);
        }

        return true;
    }

    private void checkOrder(Waiter waiter, OrderEntity orderEntity) throws MessageException {

        if (!orderEntity.getWaiter().equals(waiter))
            throw new MessageException("Order with given id not found.");

    }
}