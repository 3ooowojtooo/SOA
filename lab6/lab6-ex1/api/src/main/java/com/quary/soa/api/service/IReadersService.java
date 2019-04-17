package com.quary.soa.api.service;

import com.quary.soa.api.entity.Reader;

import java.util.List;

public interface IReadersService {

    List<Reader> getAllReaders();

    void update(Reader reader);
}
