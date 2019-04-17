package com.quary.soa.api.service;

import com.quary.soa.api.entity.Rental;
import com.quary.soa.api.exception.BookAddingException;
import com.quary.soa.api.entity.Book;
import com.quary.soa.api.exception.BookRentException;

import java.util.List;

public interface IBooksService {

    List<Book> getAllBooks();

    Book addBook(String title, String amount, String authorId) throws BookAddingException;

    void update(Book book);

    Rental rent(int bookId, String authorId, String rentalEndDate) throws BookRentException;
}
