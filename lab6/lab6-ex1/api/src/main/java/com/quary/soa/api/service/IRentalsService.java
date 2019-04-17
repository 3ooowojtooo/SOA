package com.quary.soa.api.service;

import com.quary.soa.api.entity.Rental;

import java.util.List;

public interface IRentalsService {

    List<Rental> getAllRentals();

    void removeRental(Rental rental);
}
