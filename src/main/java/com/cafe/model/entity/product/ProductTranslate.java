package com.cafe.model.entity.product;

import com.cafe.model.entity.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;

@Table(name = "ProductTranslate")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class ProductTranslate extends AbstractEntity {

    @Column(name = "Name")
    @Nationalized
    private String name;

    @Column(name = "Description")
    @Nationalized
    @Basic
    @Lob
    private String description;

    @Column(name = "LanguageName")
    private String languageName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProductId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

}