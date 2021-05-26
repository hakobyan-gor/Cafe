package com.cafe.model.dto.table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TablePreviewDto {

    private Long tableId;
    private String tableNumber;
    private int seatsCount;
    private int tableShape;
    private int tableStatus;

}