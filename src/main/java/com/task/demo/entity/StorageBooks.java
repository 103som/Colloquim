package com.task.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "storage_books")
public class StorageBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "countt", nullable = false)
    private Integer bookCount;

    public StorageBooks() {
    }

    public StorageBooks(Storage storage, Book book, Integer bookCount) {
        this.storage = storage;
        this.book = book;
        this.bookCount = bookCount;
    }

    public Storage getStorage() {
        return storage;
    }

    public Book getBook() {
        return book;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }
}
