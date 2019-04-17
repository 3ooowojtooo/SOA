package com.quary.soa.webapp.books;

import com.quary.soa.api.entity.Author;
import com.quary.soa.api.entity.Reader;
import com.quary.soa.api.service.IReadersService;
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
public class ReadersView implements Serializable {

    @EJB(lookup = "java:global/impl/ReadersService!com.quary.soa.api.service.IReadersServiceRemote")
    private IReadersService readersService;

    private List<Reader> readers;

    @PostConstruct
    public void init() {
        readers = readersService.getAllReaders();
    }

    public void onRowEdit(RowEditEvent event) {
        Reader reader = (Reader) event.getObject();
        int id = reader.getId();
        readersService.update(reader);
        FacesMessage msg = new FacesMessage("Reader edited", String.valueOf(id));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        int id = ((Reader)event.getObject()).getId();
        FacesMessage msg = new FacesMessage("Reader Cancelled", String.valueOf(id));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public IReadersService getReadersService() {
        return readersService;
    }

    public void setReadersService(IReadersService readersService) {
        this.readersService = readersService;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }
}
