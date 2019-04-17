package com.quary.soa.webapp.books;

import com.quary.soa.api.entity.Rental;
import com.quary.soa.api.service.IRentalsService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.log.Log;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Named
@ViewScoped
public class RentalsView implements Serializable {

    @EJB(lookup = "java:global/impl/RentalsService!com.quary.soa.api.service.IRentalsServiceRemote")
    private IRentalsService rentalsService;

    private List<Rental> rentals;

    private Rental selectedRental;

    private DataTable dataTable;

    @PostConstruct
    public void init() {
        rentals = rentalsService.getAllRentals();
    }

    public void onRentalRemove() {
        rentalsService.removeRental(selectedRental);
        rentals.remove(selectedRental);
        selectedRental = null;
    }

    public void onFilter() {
        Map<String, Object> filters = dataTable.getFilters();
        rentals = rentalsService.getAllWithFilters(filters);
    }

    public IRentalsService getRentalsService() {
        return rentalsService;
    }

    public void setRentalsService(IRentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public Rental getSelectedRental() {
        return selectedRental;
    }

    public void setSelectedRental(Rental selectedRental) {
        this.selectedRental = selectedRental;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }
}
