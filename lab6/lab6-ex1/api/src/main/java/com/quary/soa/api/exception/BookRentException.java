package com.quary.soa.api.exception;

public class BookRentException extends Exception {

    public BookRentException() {
        super();
    }

    public BookRentException(String message) {
        super(message);
    }

    public BookRentException(String message, Throwable cause) {
        super(message, cause);
    }

}
