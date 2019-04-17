package com.quary.soa.api.service;

import com.quary.soa.api.entity.Author;

import java.util.List;

public interface IAuthorsService {

    List<Author> getAllAuthors();

    void update(Author author);
}
