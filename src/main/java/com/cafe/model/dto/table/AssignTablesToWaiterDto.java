package com.cafe.model.dto.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AssignTablesToWaiterDto {

    @NotNull
    private Long userId;
    @NotNull
    @Size(min = 1)
    private List<Long> tableIdList;

}