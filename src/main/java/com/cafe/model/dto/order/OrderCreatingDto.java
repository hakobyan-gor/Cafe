package com.cafe.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderCreatingDto {

    private List<OrderProductCreatingDto> orderProductList;
    private Long tableId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class OrderProductCreatingDto {

        private int productQuantity;
        private Long productId;

    }

}