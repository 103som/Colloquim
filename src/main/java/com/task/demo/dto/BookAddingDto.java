package com.task.demo.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

/**
 *  Класс, представляющий собой объект, передаваемый в POST запросе на добавление на склад.
 */
public class BookAddingDto {
    private String name;

    private String author;

    private String genre;

    private BigDecimal cost;

    public BookAddingDto() {
    }

    public BookAddingDto(String name, String author, String genre, BigDecimal cost) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
