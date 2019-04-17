package com.quary.soa.impl.dao;

import com.quary.soa.api.dao.IBooksDAO;
import com.quary.soa.api.dao.IBooksDAORemote;
import com.quary.soa.api.entity.Book;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Remote(IBooksDAORemote.class)
public class BooksDAO extends BaseDAO<Book> implements IBooksDAO {

    @PersistenceContext(unitName = "lab6ex1")
    private EntityManager entityManager;

    public BooksDAO() {
        super(Book.class);
    }

    @Override
    protected int getId(Book entity) {
        return entity.getId();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
