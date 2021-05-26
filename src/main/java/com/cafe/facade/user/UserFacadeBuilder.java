package com.cafe.facade.user;

import com.cafe.model.dto.user.WaiterCreatingDto;
import com.cafe.model.dto.user.UserPreviewDto;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserFacadeBuilder {

    @Lazy
    private final UserCreatingDtoFacade mCreatingDtoFacade;
    @Lazy
    private final UserPreviewDtoFacade mPreviewDtoFacade;

    public UserFacadeBuilder(
            UserCreatingDtoFacade userCreatingDtoFacade,
            UserPreviewDtoFacade userPreviewDtoFacade
    ) {
        this.mCreatingDtoFacade = userCreatingDtoFacade;
        this.mPreviewDtoFacade = userPreviewDtoFacade;
    }

    public Long createWaiter(WaiterCreatingDto waiterCreatingDto) {
        return mCreatingDtoFacade.createWaiter(waiterCreatingDto);
    }

    public Page<UserPreviewDto> getAllWaiters(Pageable pageable) {
        return mPreviewDtoFacade.getAllWaiters(pageable);
    }
}