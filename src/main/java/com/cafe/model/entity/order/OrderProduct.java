package com.cafe.model.entity.order;

import com.cafe.model.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "OrderProduct")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class OrderProduct extends AbstractEntity {

    @Column(name = "OrderId")
    private Long orderId;

    @Column(name = "ProductId")
    private Long productId;

    @Column(name = "Price")
    private float price;

    @Column(name = "Quantity")
    private int quantity;

}