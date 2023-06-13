package com.task.demo.dto;

/**
 *  Класс, представляющий собой объект, передаваемый в POST запросе на добавление в корзину.
 */
public class BookAddingToCartDto {
    private Integer id;

    private Integer bookId;
    private Integer bookCount;

    public BookAddingToCartDto() {
    }

    public BookAddingToCartDto(Integer id, Integer bookId, Integer bookCount) {
        this.id = id;
        this.bookId = bookId;
        this.bookCount = bookCount;
    }

    public Integer getId() {
        return id;
    }
    public Integer getBookId() {
        return bookId;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }
}
