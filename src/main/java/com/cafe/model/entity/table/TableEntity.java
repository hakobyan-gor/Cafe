package com.cafe.model.entity.table;

import com.cafe.enums.TableStatus;
import com.cafe.model.entity.AbstractEntity;
import com.cafe.model.entity.user.Waiter;
import com.cafe.enums.TableShape;
import lombok.*;

import javax.persistence.*;

@Table(name = "TableEntity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class TableEntity extends AbstractEntity {

    @Column(name = "TableNumber")
    private String tableNumber;

    @Column(name = "SeatsCount")
    private int seatsCount;

    @Column(name = "TableShape")
    private TableShape tableShape;

    @Column(name = "TableStatus")
    private TableStatus tableStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WaiterId", nullable = false)
    private Waiter waiter;

}