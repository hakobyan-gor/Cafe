package com.cafe.model.dto.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserPreviewDto {

    private Long waiterId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}