package com.task.demo.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

/**
 *  Класс, представляющий собой объект, передаваемый в POST запросе на добавление на склад.
 */
public class BookAddingToStorageDto {
    private String name;

    private String location;

    private Integer booksCount;

    public BookAddingToStorageDto() {
    }

    public BookAddingToStorageDto(String name, String location, Integer booksCount) {
        this.name = name;
        this.location = location;
        this.booksCount = booksCount;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Integer getBooksCount() {
        return booksCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBooksCount(Integer booksCount) {
        this.booksCount = booksCount;
    }
}
