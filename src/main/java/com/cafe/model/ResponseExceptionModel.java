package com.cafe.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ResponseExceptionModel {

    private Boolean success;
    private HttpStatus httpStatus;
    private String message;

}