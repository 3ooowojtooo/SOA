package com.quary.soa.api.service;

import com.quary.soa.api.entity.Rental;

import java.util.List;
import java.util.Map;

public interface IRentalsService {

    List<Rental> getAllRentals();

    void removeRental(Rental rental);

    List<Rental> getAllWithFilters(Map<String, Object> filters);
}
