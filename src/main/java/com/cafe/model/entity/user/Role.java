package com.cafe.model.entity.user;

import com.cafe.model.entity.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Role extends AbstractEntity {

    @Lob
    @Basic
    @Nationalized
    @Column(name = "RoleName")
    private String roleName;

    @Column(name = "Description")
    private String description;

}