package com.quary.soa.api.dao;

import com.quary.soa.api.entity.Rental;

import java.util.List;
import java.util.Map;

public interface IRentalsDAO extends IBaseDAO<Rental> {

    List<Rental> findWithFilters(Map<String, Object> filter);
}
