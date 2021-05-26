package com.cafe.model.dto.product;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductPreviewDtoList {

    private Long id;
    private String name;
    private String coverPhotoUrl;
    private float price;

}