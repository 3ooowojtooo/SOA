package com.quary.soa.impl.dao;

import com.quary.soa.api.dao.IRentalsDAO;
import com.quary.soa.api.dao.IRentalsDAORemote;
import com.quary.soa.api.entity.Rental;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Remote(IRentalsDAORemote.class)
public class RentalsDAO extends BaseDAO<Rental> implements IRentalsDAO {

    @PersistenceContext(unitName = "lab6ex1")
    private EntityManager entityManager;

    public RentalsDAO() {
        super(Rental.class);
    }

    @Override
    protected int getId(Rental entity) {
        return entity.getId();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
