package com.cafe.model.dto.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TableCreatingDto {

    private String tableNumber;
    private int seatsCount;
    private int tableShape;
    private int tableStatus;

}