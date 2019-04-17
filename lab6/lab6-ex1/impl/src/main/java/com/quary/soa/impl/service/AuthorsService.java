package com.quary.soa.impl.service;

import com.quary.soa.api.dao.IAuthorsDAO;
import com.quary.soa.api.entity.Author;
import com.quary.soa.api.service.IAuthorsService;
import com.quary.soa.api.service.IAuthorsServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
@Remote(IAuthorsServiceRemote.class)
public class AuthorsService implements IAuthorsService {

    @EJB(lookup = "java:global/impl/AuthorsDAO!com.quary.soa.api.dao.IAuthorsDAORemote")
    private IAuthorsDAO authorsDAO;

    @Override
    public List<Author> getAllAuthors() {
        return authorsDAO.findAll();
    }

    @Override
    public void update(Author author) {
        authorsDAO.merge(author);
    }

    public IAuthorsDAO getAuthorsDAO() {
        return authorsDAO;
    }

    public void setAuthorsDAO(IAuthorsDAO authorsDAO) {
        this.authorsDAO = authorsDAO;
    }
}
