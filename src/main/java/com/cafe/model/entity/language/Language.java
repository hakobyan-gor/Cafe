package com.cafe.model.entity.language;

import com.cafe.model.entity.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Table(name = "Language")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Language extends AbstractEntity {

    @Column(name = "Name", unique = true, length = 50)
    @Nationalized
    private String name;

    @Column(name = "UniqueSeoCode")
    @Nationalized
    @Basic
    @Lob
    private String uniqueSeoCode;

}