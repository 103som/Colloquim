package com.task.demo.repository;

import com.task.demo.entity.Book;
import com.task.demo.entity.Cart;
import com.task.demo.entity.Storage;
import com.task.demo.entity.StorageBooks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageBooksRepo extends JpaRepository<StorageBooks, Long> {
    @Query("SELECT SUM(sb.bookCount) FROM StorageBooks sb WHERE sb.book = :book")
    Integer findBookCountByBook(Book book);

    @Transactional
    @Modifying
    @Query("UPDATE StorageBooks c SET c.bookCount = c.bookCount + :count WHERE c.book = :book")
    void updateBookCount(Book book, int count);

    StorageBooks findByBook(Book book);

    @Query("SELECT c FROM StorageBooks c WHERE c.book = :bookId AND c.bookCount >= :booksCount")
    List<StorageBooks> findByBookId(int bookId, int booksCount);

    @Query("SELECT c FROM StorageBooks c WHERE c.id = :ind AND c.book = :book")
    StorageBooks findByBookIdAndId(int ind, Book book);


    @Modifying
    @Query("UPDATE StorageBooks SET bookCount = bookCount + :count WHERE storage = :storage_id AND book = :book_id")
    void updateBook(Storage storage_id, Book book_id, Integer count);
}
