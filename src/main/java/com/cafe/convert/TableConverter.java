package com.cafe.convert;

import com.cafe.model.dto.table.TableCreatingDto;
import com.cafe.model.dto.table.TablePreviewDto;
import com.cafe.model.entity.table.TableEntity;
import com.cafe.enums.TableStatus;
import com.cafe.enums.TableShape;

import java.util.List;
import java.util.stream.Collectors;

public class TableConverter {

    public static TableEntity convertToEntity(TableCreatingDto tableCreatingDto) {
        return TableEntity
                .builder()
                .tableStatus(TableStatus.valueOf(tableCreatingDto.getTableStatus()).orElseThrow())
                .tableShape(TableShape.valueOf(tableCreatingDto.getTableShape()).orElseThrow())
                .tableNumber(tableCreatingDto.getTableNumber())
                .seatsCount(tableCreatingDto.getSeatsCount())
                .build();
    }

    public static List<TablePreviewDto> convertToPreviewDtoList(List<TableEntity> tableEntityList) {
        return tableEntityList.stream().map(TableConverter::convertToPreviewDto).collect(Collectors.toList());
    }

    private static TablePreviewDto convertToPreviewDto(TableEntity tableEntity) {

        return TablePreviewDto
                .builder()
                .seatsCount(tableEntity.getSeatsCount())
                .tableNumber(tableEntity.getTableNumber())
                .tableId(tableEntity.getId())
                .tableShape(tableEntity.getTableShape().getInt())
                .tableStatus(tableEntity.getTableStatus().getInt())
                .build();
    }

}