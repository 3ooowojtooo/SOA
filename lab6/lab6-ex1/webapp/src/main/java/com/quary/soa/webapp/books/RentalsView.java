package com.quary.soa.webapp.books;

import com.quary.soa.api.entity.Rental;
import com.quary.soa.api.service.IRentalsService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RentalsView implements Serializable {

    @EJB(lookup = "java:global/impl/RentalsService!com.quary.soa.api.service.IRentalsServiceRemote")
    private IRentalsService rentalsService;

    private List<Rental> rentals;

    private Rental selectedRental;

    @PostConstruct
    public void init() {
        rentals = rentalsService.getAllRentals();
    }

    public void onRentalRemove() {
        rentalsService.removeRental(selectedRental);
        rentals.remove(selectedRental);
        selectedRental = null;
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
}
