package com.cafe.facade.table;

import com.cafe.convert.TableConverter;
import com.cafe.model.dto.table.AssignTablesToWaiterDto;
import com.cafe.model.dto.table.TableCreatingDto;
import com.cafe.model.entity.table.TableEntity;
import com.cafe.model.entity.user.Waiter;
import com.cafe.repository.TableEntityRepository;
import com.cafe.utils.FindWaiter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TableCreatingDtoFacade {

    @Lazy
    private final TableEntityRepository mRepository;
    @Lazy
    private final FindWaiter mFindWaiter;

    public TableCreatingDtoFacade(
            TableEntityRepository tableEntityRepository,
            FindWaiter findWaiter
    ) {
        this.mRepository = tableEntityRepository;
        this.mFindWaiter = findWaiter;
    }

    public Long createTable(TableCreatingDto tableCreatingDto) {
        TableEntity tableEntity = TableConverter.convertToEntity(tableCreatingDto);
        return mRepository.save(tableEntity).getId();
    }

    public Boolean assignTableToWaiter(AssignTablesToWaiterDto assignTablesToWaiterDto) throws Exception {
        Waiter waiter = mFindWaiter.findById(assignTablesToWaiterDto.getUserId());
        List<TableEntity> tableEntityList = mRepository.findALLByIdIn(assignTablesToWaiterDto.getTableIdList());

        tableEntityList.forEach(tableEntity -> tableEntity.setWaiter(waiter));
        waiter.setTableEntityList(tableEntityList);
        return true;
    }

}