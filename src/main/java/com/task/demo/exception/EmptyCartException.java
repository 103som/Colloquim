package com.task.demo.exception;

public class EmptyCartException extends Exception {
    public EmptyCartException() {
        super("Корзина данного пользователя пуста!");
    }
}
