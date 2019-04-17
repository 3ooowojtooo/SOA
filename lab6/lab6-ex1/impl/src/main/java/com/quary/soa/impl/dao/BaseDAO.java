package com.quary.soa.impl.dao;

import com.quary.soa.api.dao.IBaseDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class BaseDAO<T> implements IBaseDAO<T> {

    private Class<T> entityClass;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected abstract int getId(T entity);

    @Override
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void merge(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void remove(T entity) {
        T attachedEntity = findById(getId(entity));
        getEntityManager().remove(attachedEntity);
    }

    @Override
    public T findById(int id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = getEntityManager().createQuery("SELECT c FROM " + entityClass.getName() + " c", entityClass);
        return query.getResultList();
    }
}
