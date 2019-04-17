package com.quary.soa.impl.dao;

import com.quary.soa.api.dao.IAuthorsDAO;
import com.quary.soa.api.dao.IAuthorsDAORemote;
import com.quary.soa.api.entity.Author;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Remote(IAuthorsDAORemote.class)
public class AuthorsDAO extends BaseDAO<Author> implements IAuthorsDAO {

    @PersistenceContext(unitName = "lab6ex1")
    private EntityManager entityManager;

    public AuthorsDAO() {
        super(Author.class);
    }

    @Override
    protected int getId(Author entity) {
        return entity.getId();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
