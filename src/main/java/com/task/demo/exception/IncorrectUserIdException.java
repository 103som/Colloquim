package com.task.demo.exception;

public class IncorrectUserIdException extends Exception {
    public IncorrectUserIdException() {
        super("Передан некорректный id пользователя");
    }
}
