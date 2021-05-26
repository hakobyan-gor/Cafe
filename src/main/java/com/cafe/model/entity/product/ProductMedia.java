package com.cafe.model.entity.product;

import com.cafe.model.entity.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;

@Table(name = "ProductMedia")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class ProductMedia extends AbstractEntity {

    @Column(name = "MediaUrl")
    private String mediaUrl;

    @Column(name = "IsCoverPhoto")
    private boolean isCoverImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProductId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

}