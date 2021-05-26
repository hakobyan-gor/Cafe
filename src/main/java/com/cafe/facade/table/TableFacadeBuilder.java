package com.cafe.facade.table;

import com.cafe.model.dto.table.AssignTablesToWaiterDto;
import com.cafe.model.dto.table.TableCreatingDto;
import com.cafe.model.dto.table.TablePreviewDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TableFacadeBuilder {

    @Lazy
    private final TableCreatingDtoFacade mCreatingDtoFacade;
    @Lazy
    private final TablePreviewDtoFacade mPreviewDtoFacade;

    public TableFacadeBuilder(
            TableCreatingDtoFacade tableCreatingDtoFacade,
            TablePreviewDtoFacade tablePreviewDtoFacade
    ) {
        this.mCreatingDtoFacade = tableCreatingDtoFacade;
        this.mPreviewDtoFacade = tablePreviewDtoFacade;
    }

    public Long createTable(TableCreatingDto tableCreatingDto) {
        return mCreatingDtoFacade.createTable(tableCreatingDto);
    }

    public Boolean assignTableToWaiter(AssignTablesToWaiterDto assignTablesToWaiterDto) throws Exception {
        return mCreatingDtoFacade.assignTableToWaiter(assignTablesToWaiterDto);
    }

    public List<TablePreviewDto> getWaiterTables(Authentication authentication) throws Exception {
        return mPreviewDtoFacade.getWaiterTables(authentication);
    }
}