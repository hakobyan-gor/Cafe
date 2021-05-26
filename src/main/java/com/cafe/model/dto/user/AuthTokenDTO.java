package com.cafe.model.dto.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthTokenDTO {

    private String token;
    private String role;

}
