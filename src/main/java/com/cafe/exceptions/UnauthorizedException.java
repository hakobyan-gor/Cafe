package com.cafe.exceptions;

import com.cafe.model.ResponseExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UnauthorizedException extends UsernameNotFoundException {

    public UnauthorizedException(String message) {
        super(message);
        unauthorizedException(message);
    }

    public ResponseExceptionModel unauthorizedException(String message) {
        return ResponseExceptionModel.builder()
                .message(message)
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .success(false)
                .build();
    }
}