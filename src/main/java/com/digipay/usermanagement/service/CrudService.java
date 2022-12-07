package com.digipay.usermanagement.service;

import java.util.Set;

public interface CrudService<T, I> {

    Set<T> findAll();

    T findById(I id);

    T save(T object);

    T update(T object, I id);

    void delete(I id);
}