package com.cafe.model.entity.product;

import com.cafe.enums.ProductStatus;
import com.cafe.model.entity.AbstractEntity;
import com.cafe.model.entity.order.OrderEntity;
import org.hibernate.annotations.*;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;

@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Product extends AbstractEntity {

    @Column(name = "Name")
    @Nationalized
    private String name;

    @Column(name = "Description")
    @Nationalized
    @Basic
    @Lob
    private String description;

    @Column(name = "Price")
    private float price;

    @Column(name = "Ingredients")
    @Nationalized
    private String ingredients;

    @Column(name = "Calories")
    private float calories;

    @Column(name = "ProductUniqueCode")
    private String productUniqueCode;

    @Column(name = "RateAverage")
    private double rateAverage;

    @Column(name = "ProductStatus")
    private ProductStatus productStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductTranslate> productTranslateList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductMedia> productMediaList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OrderProduct", joinColumns = @JoinColumn(name = "ProductId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "OrderId", referencedColumnName = "id", nullable = false))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderEntity> orderList;

}