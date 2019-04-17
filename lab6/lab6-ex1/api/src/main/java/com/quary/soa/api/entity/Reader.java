package com.quary.soa.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "readers")
public class Reader implements Serializable {
    @Id
    @GeneratedValue(generator = "reader_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reader_generator", sequenceName = "readers_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "reader", fetch = FetchType.EAGER)
    private List<Rental> rentals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void addRental(Rental rental) {
        if(rentals.contains(rental))
            return;
        rentals.add(rental);
        rental.setReader(this);
    }

    public void removeRental(Rental rental) {
        if(!rentals.contains(rental))
            return;
        rentals.remove(rental);
        rental.setReader(null);
    }
}
