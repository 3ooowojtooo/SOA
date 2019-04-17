package com.quary.soa.webapp.books;

import com.quary.soa.api.entity.Rental;
import com.quary.soa.api.exception.BookAddingException;
import com.quary.soa.api.entity.Book;
import com.quary.soa.api.exception.BookRentException;
import com.quary.soa.api.service.IBooksService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BooksView implements Serializable {

    @EJB(lookup = "java:global/impl/BooksService!com.quary.soa.api.service.IBooksServiceRemote")
    private IBooksService booksService;

    private List<Book> books;

    private String newBookTitle;
    private String newBookAmount;
    private String newBookAuthorId;

    private Book selectedBook;

    private String newRentalReaderId;
    private String newRentalEndDate;

    @PostConstruct
    public void init() {
        books = booksService.getAllBooks();
    }

    public void onRowEdit(RowEditEvent event) {
        Book book = (Book)event.getObject();
        int id = book.getId();
        booksService.update(book);
        FacesMessage msg = new FacesMessage("Book Edited", String.valueOf(id));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        int id = ((Book)event.getObject()).getId();
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(id));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddBook() {
        try {
            Book addedBook = booksService.addBook(newBookTitle, newBookAmount, newBookAuthorId);
            books.add(addedBook);
            FacesMessage msg = new FacesMessage("Book added", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (BookAddingException e) {
            FacesMessage msg = new FacesMessage(e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRentBook() {
        try {
            booksService.rent(selectedBook.getId(), newRentalReaderId, newRentalEndDate);
            FacesMessage msg = new FacesMessage("Rental added", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            books = booksService.getAllBooks();
        } catch (BookRentException e) {
            FacesMessage msg = new FacesMessage(e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public IBooksService getBooksService() {
        return booksService;
    }

    public void setBooksService(IBooksService booksService) {
        this.booksService = booksService;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getNewBookTitle() {
        return newBookTitle;
    }

    public void setNewBookTitle(String newBookTitle) {
        this.newBookTitle = newBookTitle;
    }

    public String getNewBookAmount() {
        return newBookAmount;
    }

    public void setNewBookAmount(String newBookAmount) {
        this.newBookAmount = newBookAmount;
    }

    public String getNewBookAuthorId() {
        return newBookAuthorId;
    }

    public void setNewBookAuthorId(String newBookAuthorId) {
        this.newBookAuthorId = newBookAuthorId;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public String getNewRentalReaderId() {
        return newRentalReaderId;
    }

    public void setNewRentalReaderId(String newRentalReaderId) {
        this.newRentalReaderId = newRentalReaderId;
    }

    public String getNewRentalEndDate() {
        return newRentalEndDate;
    }

    public void setNewRentalEndDate(String newRentalEndDate) {
        this.newRentalEndDate = newRentalEndDate;
    }
}
