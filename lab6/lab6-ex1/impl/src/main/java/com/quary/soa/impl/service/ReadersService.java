package com.quary.soa.impl.service;

import com.quary.soa.api.dao.IReadersDAO;
import com.quary.soa.api.entity.Reader;
import com.quary.soa.api.service.IReadersService;
import com.quary.soa.api.service.IReadersServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
@Remote(IReadersServiceRemote.class)
public class ReadersService implements IReadersService {

    @EJB(lookup = "java:global/impl/ReadersDAO!com.quary.soa.api.dao.IReadersDAORemote")
    private IReadersDAO readersDAO;

    @Override
    public List<Reader> getAllReaders() {
        return readersDAO.findAll();
    }

    @Override
    public void update(Reader reader) {
        readersDAO.merge(reader);
    }

    public IReadersDAO getReadersDAO() {
        return readersDAO;
    }

    public void setReadersDAO(IReadersDAO readersDAO) {
        this.readersDAO = readersDAO;
    }
}
