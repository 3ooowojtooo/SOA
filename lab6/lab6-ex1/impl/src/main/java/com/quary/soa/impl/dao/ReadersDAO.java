package com.quary.soa.impl.dao;

import com.quary.soa.api.dao.IReadersDAO;
import com.quary.soa.api.dao.IReadersDAORemote;
import com.quary.soa.api.entity.Reader;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Remote(IReadersDAORemote.class)
public class ReadersDAO extends BaseDAO<Reader> implements IReadersDAO {

    @PersistenceContext(unitName = "lab6ex1")
    private EntityManager entityManager;

    public ReadersDAO() {
        super(Reader.class);
    }

    @Override
    protected int getId(Reader entity) {
        return entity.getId();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
