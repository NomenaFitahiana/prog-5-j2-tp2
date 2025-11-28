package com.school.HEI.service;

import java.util.List;
import java.util.UUID;

public interface ServiceInterface<T> {
    List<T> getAll();
    T getById(UUID id);
    T create(T entity);
    T update(T entity);
    void delete(UUID id);
}
