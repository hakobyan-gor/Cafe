package com.cafe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.cafe.model.entity.AbstractEntity;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

    Iterable<E> findAllByDeletedFalse();

    Page<E> findAllByDeletedFalse(Pageable pageable);

    Page<E> findAll(Pageable pageable);

    Page<E> findAll(Specification<E> specification, Pageable page);

    List<E> findAll(Specification<E> specification);

    List<E> findALLByIdIn(List<Long> ids);

}