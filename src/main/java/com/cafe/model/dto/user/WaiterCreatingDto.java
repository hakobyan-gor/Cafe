package com.cafe.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WaiterCreatingDto {

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String patronymic;
    @NotNull
    private Long dateOfBirth;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;

}