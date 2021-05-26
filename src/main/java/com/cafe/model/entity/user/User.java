package com.cafe.model.entity.user;

import com.cafe.model.entity.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Table(name = "AppUser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class User extends AbstractEntity {

    @Column(name = "FirstName")
    @Nationalized
    private String firstName;

    @Column(name = "LastName")
    @Nationalized
    private String lastName;

    @Column(name = "Patronymic")
    private String patronymic;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "DateOfBirth")
    private Long dateOfBirth;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoleId")
    private Role role;

}