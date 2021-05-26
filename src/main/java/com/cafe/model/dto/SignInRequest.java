package com.cafe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignInRequest {

    @Size(min = 6, message = "Username must contain minimum 6 characters")
    @NotNull
    private String username;
    @Size(min = 8, message = "Username must contain minimum 8 characters")
    @NotNull
    private String password;

}