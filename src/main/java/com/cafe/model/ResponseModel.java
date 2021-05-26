package com.cafe.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ResponseModel<T> implements Serializable {

    private boolean success;
    private String message;
    private T data;

}