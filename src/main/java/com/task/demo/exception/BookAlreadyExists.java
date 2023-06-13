package com.task.demo.exception;

public class BookAlreadyExists extends Exception {
    public BookAlreadyExists(String message) {
        super(message);
    }
}
