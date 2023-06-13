package com.task.demo.exception;

public class BookDoesntExistException extends Exception{
    public BookDoesntExistException(String message) {
        super(message);
    }
}
