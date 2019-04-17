package com.quary.soa.api.dao;

import java.util.List;

public interface IBaseDAO<T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(int id);

    List<T> findAll();

}
