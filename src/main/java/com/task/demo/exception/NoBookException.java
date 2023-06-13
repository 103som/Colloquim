package com.task.demo.exception;

public class NoBookException extends Exception{
    public NoBookException() {
        super("Указанной книги нет в каталоге.");
    }
}
