package com.cafe.convert;

import com.cafe.model.dto.user.WaiterCreatingDto;
import com.cafe.model.dto.user.UserPreviewDto;
import org.springframework.data.domain.Page;
import com.cafe.model.entity.user.Waiter;

public class WaiterConverter {

    public static Waiter convertToEntity(WaiterCreatingDto waiterCreatingDto) {
        Waiter waiter = new Waiter();
        waiter.setEmail(waiterCreatingDto.getEmail());
        waiter.setPhoneNumber(waiterCreatingDto.getPhoneNumber());
        waiter.setFirstName(waiterCreatingDto.getFirstname());
        waiter.setLastName(waiterCreatingDto.getLastname());
        waiter.setPatronymic(waiterCreatingDto.getPatronymic());
        waiter.setDateOfBirth(waiterCreatingDto.getDateOfBirth());
        return waiter;
    }

    public static Page<UserPreviewDto> convertToPreviewDtoList(Page<Waiter> waiterPage) {
        return waiterPage.map(WaiterConverter::convertToPreviewDto);
    }

    private static UserPreviewDto convertToPreviewDto(Waiter waiter) {
        return UserPreviewDto
                .builder()
                .phoneNumber(waiter.getPhoneNumber())
                .firstName(waiter.getFirstName())
                .lastName(waiter.getLastName())
                .waiterId(waiter.getId())
                .build();
    }
}