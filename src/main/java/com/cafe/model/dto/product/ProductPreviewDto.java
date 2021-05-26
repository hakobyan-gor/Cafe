package com.cafe.model.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductPreviewDto {

    private String name;
    private String description;
    private Long id;
    private float calories;
    private float price;
    private String ingredients;
    private List<ProductMediaPreviewDto> mediaPreviewList;

    @Builder
    @Data
    public static class ProductMediaPreviewDto {

        private String mediaUrl;
        private boolean isCoverPhoto;

    }

}