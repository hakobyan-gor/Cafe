package com.cafe.utils;

import com.cafe.enums.TableStatus;
import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.exceptions.MessageException;
import com.cafe.model.entity.table.TableEntity;
import com.cafe.repository.TableEntityRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindTable {

    @Lazy
    private final TableEntityRepository mRepository;

    public FindTable(TableEntityRepository tableEntityRepository) {
        this.mRepository = tableEntityRepository;
    }

    public TableEntity findById(Long tableId) throws Exception {
        Optional<TableEntity> optionalTableEntity = mRepository.findById(tableId);

        if (optionalTableEntity.isEmpty())
            throw new EntityNotFoundException(TableEntity.class, "Id", String.valueOf(tableId));

        TableEntity tableEntity = optionalTableEntity.get();

        if (tableEntity.getTableStatus().equals(TableStatus.BUSY))
            throw new MessageException("Table is busy.");

        return tableEntity;
    }

}