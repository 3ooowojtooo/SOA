package com.quary.soa.api.entity;

import com.sun.source.doctree.SerialDataTree;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(generator = "book_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_generator", sequenceName = "books_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Rental> rentals;

    public Book() {
        super();
    }

    public Book(String title, int amount) {
        this.title = title;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        author.addBook(this);
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void addRental(Rental rental) {
        if (rentals.contains(rental))
            return;
        rentals.add(rental);
        rental.setBook(this);
    }

    public void removeRental(Rental rental) {
        if (!rentals.contains(rental))
            return;
        rentals.remove(rental);
        rental.setBook(null);
    }
}