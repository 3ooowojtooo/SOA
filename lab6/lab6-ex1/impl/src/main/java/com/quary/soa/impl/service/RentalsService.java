package com.quary.soa.impl.service;

import com.quary.soa.api.dao.IBooksDAO;
import com.quary.soa.api.dao.IReadersDAO;
import com.quary.soa.api.dao.IRentalsDAO;
import com.quary.soa.api.entity.Book;
import com.quary.soa.api.entity.Reader;
import com.quary.soa.api.entity.Rental;
import com.quary.soa.api.service.IRentalsService;
import com.quary.soa.api.service.IRentalsServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
@Remote(IRentalsServiceRemote.class)
public class RentalsService implements IRentalsService {

    @EJB(lookup = "java:global/impl/RentalsDAO!com.quary.soa.api.dao.IRentalsDAORemote")
    private IRentalsDAO rentalsDAO;

    @EJB(lookup = "java:global/impl/BooksDAO!com.quary.soa.api.dao.IBooksDAORemote")
    private IBooksDAO booksDAO;

    @EJB(lookup = "java:global/impl/ReadersDAO!com.quary.soa.api.dao.IReadersDAORemote")
    private IReadersDAO readersDAO;

    @Override
    public List<Rental> getAllRentals() {
        return rentalsDAO.findAll();
    }

    @Override
    public void removeRental(Rental rental) {
        Book book = rental.getBook();
        book.setAmount(book.getAmount() + 1);
        booksDAO.merge(book);
        rentalsDAO.remove(rental);
    }

    public IRentalsDAO getRentalsDAO() {
        return rentalsDAO;
    }

    public void setRentalsDAO(IRentalsDAO rentalsDAO) {
        this.rentalsDAO = rentalsDAO;
    }

    public IBooksDAO getBooksDAO() {
        return booksDAO;
    }

    public void setBooksDAO(IBooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public IReadersDAO getReadersDAO() {
        return readersDAO;
    }

    public void setReadersDAO(IReadersDAO readersDAO) {
        this.readersDAO = readersDAO;
    }
}
