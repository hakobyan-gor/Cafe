package com.cafe.utils;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.exceptions.UnauthorizedException;
import com.cafe.model.entity.user.Waiter;
import com.cafe.repository.WaiterRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindWaiter {

    private final WaiterRepository mWaiterRepository;

    public FindWaiter(WaiterRepository waiterRepository) {
        this.mWaiterRepository = waiterRepository;
    }

    public Waiter findById(Long waiterId) throws EntityNotFoundException {
        Optional<Waiter> optionalWaiter = mWaiterRepository.findById(waiterId);

        if (optionalWaiter.isEmpty())
            throw new EntityNotFoundException(Waiter.class, "Id", String.valueOf(waiterId));

        return optionalWaiter.get();
    }

    public Waiter findByToken(Authentication authentication) throws UnauthorizedException {
        Optional<Waiter> optionalUser = mWaiterRepository.findByUsername(authentication.getName());
        if (optionalUser.isEmpty()) {
            throw new UnauthorizedException("You are not authorized");
        }
        return  optionalUser.get();
    }

}