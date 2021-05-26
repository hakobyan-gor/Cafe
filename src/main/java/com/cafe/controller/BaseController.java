package com.cafe.controller;

import com.cafe.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class BaseController {

    public <R> ResponseModel createSuccessResult(R data, String message) {
        return ResponseModel
                .builder()
                .message(message)
                .success(true)
                .data(data)
                .build();
    }

    public ResponseModel createErrorResult(Exception e) {
        return ResponseModel
                .builder()
                .message(e.getMessage())
                .success(false)
                .data(null)
                .build();
    }

    protected ResponseModel<?> createErrorResult(String errorMessage){
        return ResponseModel.builder()
                .data(null)
                .message(errorMessage)
                .success(false)
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel handleException(MethodArgumentNotValidException exception) {
        return ResponseModel
                .builder()
                .message(getFieldError(exception.getBindingResult().getFieldErrors()))
                .success(false)
                .data(null)
                .build();
    }

    private String getFieldError(List<FieldError> fieldErrors) {
        if (!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            return String.format("Field '%s' %s", fieldError.getField(), fieldError.getDefaultMessage());
        }
        return "Validation error";
    }

}