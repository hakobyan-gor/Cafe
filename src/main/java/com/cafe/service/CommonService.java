package com.cafe.service;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.entity.AbstractEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface CommonService<E extends AbstractEntity> {

    E save(E entity) throws EntityNotFoundException;

    Iterable<E> saveAll(Iterable<E> iterable);

    Iterable<E> listAll();

    Page<E> listAll(Pageable pageable);

    E getById(Long id) throws EntityNotFoundException;

    E getByIdWithHidden(Long id) throws EntityNotFoundException;

    boolean delete(Long id) throws Exception;

    void remove(E entity);

    Long count();
}