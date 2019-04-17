package com.quary.soa.api.exception;

public class BookAddingException extends Exception {

    public BookAddingException() {
        super();
    }

    public BookAddingException(String message) {
        super(message);
    }

    public BookAddingException(String message, Throwable cause) {
        super(message, cause);
    }
}
