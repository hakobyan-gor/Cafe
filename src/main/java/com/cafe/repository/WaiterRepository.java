package com.cafe.repository;

import com.cafe.model.entity.user.Waiter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaiterRepository extends CommonRepository<Waiter> {

    Optional<Waiter> findByUsername(String username);
}
