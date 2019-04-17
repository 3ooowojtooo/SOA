package com.quary.soa.impl.service;

import com.quary.soa.api.dao.IReadersDAO;
import com.quary.soa.api.dao.IRentalsDAO;
import com.quary.soa.api.entity.Reader;
import com.quary.soa.api.entity.Rental;
import com.quary.soa.api.exception.BookAddingException;
import com.quary.soa.api.dao.IAuthorsDAO;
import com.quary.soa.api.dao.IBooksDAO;
import com.quary.soa.api.entity.Author;
import com.quary.soa.api.entity.Book;
import com.quary.soa.api.exception.BookRentException;
import com.quary.soa.api.service.IBooksService;
import com.quary.soa.api.service.IBooksServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Singleton
@Remote(IBooksServiceRemote.class)
public class BooksService implements IBooksService {

    @EJB(lookup = "java:global/impl/BooksDAO!com.quary.soa.api.dao.IBooksDAORemote")
    private IBooksDAO booksDAO;

    @EJB(lookup = "java:global/impl/AuthorsDAO!com.quary.soa.api.dao.IAuthorsDAORemote")
    private IAuthorsDAO authorsDAO;

    @EJB(lookup = "java:global/impl/ReadersDAO!com.quary.soa.api.dao.IReadersDAORemote")
    private IReadersDAO readersDAO;

    @EJB(lookup = "java:global/impl/RentalsDAO!com.quary.soa.api.dao.IRentalsDAORemote")
    private IRentalsDAO rentalsDAO;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Book> getAllBooks() {
        return booksDAO.findAll();
    }

    @Override
    public Book addBook(String title, String amount, String authorId) throws BookAddingException {
        int amountParsed, authorIdParsed;
        try {
            amountParsed = Integer.parseInt(amount);
            authorIdParsed = Integer.parseInt(authorId);
        } catch (NumberFormatException e) {
            throw new BookAddingException("Amount and authorId must be integers.", e);
        }

        Author author = authorsDAO.findById(authorIdParsed);
        if (author == null) {
            throw new BookAddingException("Author with specified id doesn`t exists.");
        }

        Book book = new Book(title, amountParsed);
        book.setAuthor(author);
        booksDAO.persist(book);
        return book;
    }

    @Override
    public void update(Book book) {
        booksDAO.merge(book);
    }

    @Override
    public Rental rent(int bookId, String readerId, String rentalEndDate) throws BookRentException {
        int readerIdParsed;
        try {
            readerIdParsed = Integer.parseInt(readerId);
        } catch (NumberFormatException e) {
            throw new BookRentException("ReaderId must be an integer.", e);
        }

        Reader reader = readersDAO.findById(readerIdParsed);

        if (reader == null) {
            throw new BookRentException("Reader with specified id doesn`t exists.");
        }

        Book book = booksDAO.findById(bookId);

        if (book.getAmount() == 0) {
            throw new BookRentException("We don`t have this book for now.");
        }

        Date rentalEndDateParsed;
        try {
            rentalEndDateParsed = DATE_FORMAT.parse(rentalEndDate);
        } catch (ParseException e) {
            throw new BookRentException("Date must be provided in YYYY-MM-DD format.", e);
        }

        Date rentalStartDate = new Date();
        Rental rental = new Rental(rentalStartDate, rentalEndDateParsed);
        rental.setBook(book);
        rental.setReader(reader);
        rentalsDAO.persist(rental);
        book.setAmount(book.getAmount() - 1);
        booksDAO.merge(book);
        return rental;
    }

    public IBooksDAO getBooksDAO() {
        return booksDAO;
    }

    public void setBooksDAO(IBooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public IAuthorsDAO getAuthorsDAO() {
        return authorsDAO;
    }

    public void setAuthorsDAO(IAuthorsDAO authorsDAO) {
        this.authorsDAO = authorsDAO;
    }

    public IReadersDAO getReadersDAO() {
        return readersDAO;
    }

    public void setReadersDAO(IReadersDAO readersDAO) {
        this.readersDAO = readersDAO;
    }

    public IRentalsDAO getRentalsDAO() {
        return rentalsDAO;
    }

    public void setRentalsDAO(IRentalsDAO rentalsDAO) {
        this.rentalsDAO = rentalsDAO;
    }
}
