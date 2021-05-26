package com.cafe.model.entity.order;

import com.cafe.enums.OrderStatus;
import com.cafe.model.entity.AbstractEntity;
import com.cafe.model.entity.product.Product;
import com.cafe.model.entity.user.Waiter;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Table(name = "OrderEntity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class OrderEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WaiterId", nullable = false)
    private Waiter waiter;

    @Column(name = "TableId")
    private Long tableId;

    @Column(name = "OrderCanceledDate")
    private Long orderCanceledDate;

    @Column(name = "OrderFinishedDate")
    private Long orderFinishedDate;

    @Column(name = "ProductsTotalPrice")
    private float productsTotalPrice;

    @Column(name = "ServiceFee")
    private float serviceFee;

    @Column(name = "OrderTotalPrice")
    private float orderTotalPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OrderProduct", joinColumns = @JoinColumn(name = "OrderId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ProductId", referencedColumnName = "id", nullable = false))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Product> productList;

    private OrderStatus orderStatus;

}