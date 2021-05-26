package com.cafe.controller;

import com.cafe.facade.table.TableFacadeBuilder;
import com.cafe.model.ResponseModel;
import com.cafe.model.dto.table.AssignTablesToWaiterDto;
import com.cafe.model.dto.table.TableCreatingDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("table")
public class TableController extends BaseController {

    private final TableFacadeBuilder mFacadeBuilder;

    public TableController(TableFacadeBuilder tableFacadeBuilder) {
        this.mFacadeBuilder = tableFacadeBuilder;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseModel createProduct(@Valid @RequestBody TableCreatingDto tableCreatingDto) {
        try {
            return createSuccessResult(mFacadeBuilder.createTable(tableCreatingDto), "Product created successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("assignTableToWaiter")
    public ResponseModel assignTableToWaiter(AssignTablesToWaiterDto assignTablesToWaiterDto) {
        try {
            return createSuccessResult(mFacadeBuilder.assignTableToWaiter(assignTablesToWaiterDto), "Tables assigned to user successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasRole('WAITER')")
    @GetMapping("getWaiterTables")
    public ResponseModel getWaiterTables(Authentication authentication) {
        try {
            return createSuccessResult(mFacadeBuilder.getWaiterTables(authentication), "Waiter's Tables retrieved successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

}