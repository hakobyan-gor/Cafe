package com.cafe.facade.table;

import com.cafe.convert.TableConverter;
import com.cafe.model.dto.table.TablePreviewDto;
import com.cafe.model.entity.table.TableEntity;
import com.cafe.model.entity.user.Waiter;
import com.cafe.repository.TableEntityRepository;
import com.cafe.utils.FindWaiter;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TablePreviewDtoFacade {

    @Lazy
    private final TableEntityRepository mRepository;
    @Lazy
    private final FindWaiter mFindWaiter;

    public TablePreviewDtoFacade(
            TableEntityRepository tableEntityRepository,
            FindWaiter findWaiter
    ) {
        this.mRepository = tableEntityRepository;
        this.mFindWaiter = findWaiter;
    }

    public List<TablePreviewDto> getWaiterTables(Authentication authentication) throws Exception {
        Waiter waiter = mFindWaiter.findByToken(authentication);

        List<TableEntity> tableEntityList = mRepository.findAllByWaiterId(waiter.getId());
        return TableConverter.convertToPreviewDtoList(tableEntityList);
    }

}