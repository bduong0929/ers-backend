package com.jean.ers.daos;

import java.util.List;

public interface CrudDAO<T> {
    void save(T obj);

    void update(String id);

    void delete(String id);

    T findById(String id);

    List<T> findAllById(String id);

    List<T> findAll();
}
