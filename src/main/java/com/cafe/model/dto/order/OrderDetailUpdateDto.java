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
public class OrderDetailUpdateDto {

    private List<OrderDetail> orderDetailList;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class OrderDetail {
        private Long productId;
        private int quantity;
    }

}