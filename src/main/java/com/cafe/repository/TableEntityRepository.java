package com.cafe.repository;

import com.cafe.model.entity.table.TableEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableEntityRepository extends CommonRepository<TableEntity> {

    List<TableEntity> findAllByWaiterId(Long waiterId);

}