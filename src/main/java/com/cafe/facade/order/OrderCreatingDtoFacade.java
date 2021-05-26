package com.cafe.facade.order;

import com.cafe.enums.OrderStatus;
import com.cafe.exceptions.MessageException;
import com.cafe.model.dto.order.OrderCreatingDto;
import com.cafe.model.entity.order.OrderEntity;
import com.cafe.model.entity.order.OrderProduct;
import com.cafe.model.entity.product.Product;
import com.cafe.model.entity.table.TableEntity;
import com.cafe.model.entity.user.Waiter;
import com.cafe.repository.OrderProductRepository;
import com.cafe.repository.OrderRepository;
import com.cafe.service.product.ProductService;
import com.cafe.utils.FindTable;
import com.cafe.utils.FindWaiter;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderCreatingDtoFacade {

    @Lazy
    private final OrderProductRepository orderProductRepository;
    @Lazy
    private final ProductService productService;
    @Lazy
    private final OrderRepository mRepository;
    @Lazy
    private final FindWaiter mFindWaiter;
    @Lazy
    private final FindTable mFindTable;

    public OrderCreatingDtoFacade(
            OrderProductRepository orderProductRepository,
            OrderRepository orderRepository,
            ProductService productService,
            FindWaiter mFindWaiter,
            FindTable mFindTable
    ) {
        this.orderProductRepository = orderProductRepository;
        this.productService = productService;
        this.mRepository = orderRepository;
        this.mFindWaiter = mFindWaiter;
        this.mFindTable = mFindTable;
    }

    public Long createOrder(OrderCreatingDto orderCreatingDto, Authentication authentication) throws Exception {
        Waiter waiter = mFindWaiter.findByToken(authentication);
        TableEntity tableEntity = mFindTable.findById(orderCreatingDto.getTableId());
        if (!tableEntity.getWaiter().equals(waiter))
            throw new MessageException("You cannot create order on other's table.");
        List<Product> productList = productService.findAllByIdIn(orderCreatingDto.getOrderProductList().stream().
                map(OrderCreatingDto.OrderProductCreatingDto::getProductId).collect(Collectors.toList()));
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(OrderStatus.PENDING);
        orderEntity.setTableId(tableEntity.getId());
        orderEntity.setWaiter(waiter);
        orderEntity.setProductList(productList);
        // Logic for service not implemented
        orderEntity.setServiceFee(1000);
        float productsTotalPrice = 0;
        for (OrderCreatingDto.OrderProductCreatingDto orderProductCreatingDto : orderCreatingDto.getOrderProductList()) {
            productsTotalPrice += productList.stream().filter(product ->
                    product.getId().equals(orderProductCreatingDto.getProductId())).map(Product::getPrice).findFirst().get() *
                    orderProductCreatingDto.getProductQuantity();
        }
        orderEntity.setProductsTotalPrice(productsTotalPrice);
        orderEntity.setOrderTotalPrice(orderEntity.getProductsTotalPrice() + orderEntity.getServiceFee());

        mRepository.save(orderEntity);

        for (Product product : productList) {
            OrderProduct orderProduct = OrderProduct
                    .builder()
                    .orderId(orderEntity.getId())
                    .price(product.getPrice())
                    .productId(product.getId())
                    .build();

            orderProductRepository.save(orderProduct);
        }

        return mRepository.save(orderEntity).getId();
    }

}