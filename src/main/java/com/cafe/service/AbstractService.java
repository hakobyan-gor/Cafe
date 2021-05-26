package com.cafe.service;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.entity.AbstractEntity;
import com.cafe.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    private final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E model) {
        if (model.getDeleted() == null)
            model.setDeleted(false);
        return repository.save(model);
    }

    @Override
    public Iterable<E> saveAll(Iterable<E> iterable) {
        return repository.saveAll(iterable);
    }

    @Override
    public Iterable<E> listAll() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public Page<E> listAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable);
    }

    @Override
    public E getById(Long id) throws EntityNotFoundException {
        if (repository.findById(id).isEmpty() || repository.findById(id).get().getDeleted().equals(true))
            throw new EntityNotFoundException(repository.findById(id).getClass(), "id", String.valueOf(id));

        return repository.findById(id).orElse(null);
    }

    @Override
    public E getByIdWithHidden(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void remove(E entity) {
        repository.delete(entity);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (repository.findById(id).isEmpty())
            throw new EntityNotFoundException(repository.findById(id).getClass(), "id", String.valueOf(id));
        repository.deleteById(id);
        return true;
    }

}