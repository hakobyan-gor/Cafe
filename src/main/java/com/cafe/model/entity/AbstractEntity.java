package com.cafe.model.entity;

import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public class AbstractEntity {

    @ApiModelProperty(notes = "The database generated entity id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @Id
    private Long id;

    @CreatedBy
    @Column(name = "CreatedBy")
    private String createdBy;

    @CreatedDate
    @Column(name = "CreatedDate")
    private Long createdDate;

    @LastModifiedBy
    @Column(name = "LastModifiedBy")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LastModifiedDate")
    private Long lastModifiedDate;

    @Column(name = "Deleted", columnDefinition = "bit DEFAULT 0", nullable = false)
    private Boolean deleted = false;

}