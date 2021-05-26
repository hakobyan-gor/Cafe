package com.cafe.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCreatingDto {

    @NotNull
    private String name;
    @NotNull
    private String description;
    private float price;
    private float calories;
    @NotNull
    private String ingredients;
    private List<ProductTranslateCreatingDto> translateList;
    private int productStatus;
    @NotNull
    private String productUniqueCode;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class ProductTranslateCreatingDto {

        private String name;
        private String description;
        private String languageName;

    }

}