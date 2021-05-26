package com.cafe.model.entity.user;

import com.cafe.model.entity.order.OrderEntity;
import com.cafe.model.entity.table.TableEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Waiter extends User {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waiter")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TableEntity> tableEntityList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waiter")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderEntity> orderList;

}