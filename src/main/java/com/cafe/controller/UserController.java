package com.cafe.controller;

import com.cafe.facade.user.UserFacadeBuilder;
import com.cafe.model.ResponseModel;
import com.cafe.model.dto.user.WaiterCreatingDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user/")
public class UserController extends BaseController {

    @Lazy
    private final UserFacadeBuilder mFacadeBuilder;

    public UserController(UserFacadeBuilder userFacadeBuilder) {
        this.mFacadeBuilder = userFacadeBuilder;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("createWaiter")
    public ResponseModel createWaiter(@Valid @RequestBody WaiterCreatingDto waiterCreatingDto) {
        try {
            return createSuccessResult(mFacadeBuilder.createWaiter(waiterCreatingDto), "Waiter created successfully");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("getAllWaiters/{page}/{size}")
    public ResponseModel getAllWaiters(@PathVariable int page, @PathVariable int size) {
        try {
            return createSuccessResult(mFacadeBuilder.getAllWaiters(PageRequest.of(page, size)), "Waiters retrieved successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

}