package com.task.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "orderr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "countt", nullable = false)
    private Integer bookCount;

    public Order() {
    }

    public Order(Integer userId, Book book, Integer bookCount) {
        this.userId = userId;
        this.book = book;
        this.bookCount = bookCount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Book getBook() {
        return book;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }
}
