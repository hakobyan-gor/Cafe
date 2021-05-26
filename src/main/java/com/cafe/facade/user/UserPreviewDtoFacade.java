package com.cafe.facade.user;

import com.cafe.convert.WaiterConverter;
import com.cafe.model.dto.user.UserPreviewDto;
import com.cafe.model.entity.user.Waiter;
import com.cafe.repository.WaiterRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserPreviewDtoFacade {

    @Lazy
    private final WaiterRepository mRepository;

    public UserPreviewDtoFacade(WaiterRepository waiterRepository) {
        this.mRepository = waiterRepository;
    }

    public Page<UserPreviewDto> getAllWaiters(Pageable pageable) {
        Page<Waiter> waiterPage = mRepository.findAll(pageable);
        return WaiterConverter.convertToPreviewDtoList(waiterPage);
    }

}
