package com.cafe.repository;

import com.cafe.model.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    Optional<User> findUserByEmail(String email);

}