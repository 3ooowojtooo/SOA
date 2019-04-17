package com.quary.soa.webapp.books;

import com.quary.soa.api.entity.Author;
import com.quary.soa.api.entity.Book;
import com.quary.soa.api.service.IAuthorsService;
import com.sun.security.jgss.AuthorizationDataEntry;
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
public class AuthorsView implements Serializable {

    @EJB(lookup = "java:global/impl/AuthorsService!com.quary.soa.api.service.IAuthorsServiceRemote")
    private IAuthorsService authorsService;

    private List<Author> authors;

    @PostConstruct
    public void init() {
        authors = authorsService.getAllAuthors();
    }

    public void onRowEdit(RowEditEvent event) {
        Author author = (Author) event.getObject();
        int id = author.getId();
        authorsService.update(author);
        FacesMessage msg = new FacesMessage("Author edited", String.valueOf(id));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        int id = ((Author)event.getObject()).getId();
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(id));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public IAuthorsService getAuthorsService() {
        return authorsService;
    }

    public void setAuthorsService(IAuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
