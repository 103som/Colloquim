package com.task.demo.repository;

import com.task.demo.entity.Book;
import com.task.demo.entity.Cart;
import com.task.demo.entity.StorageBooks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    Cart findCartByBook(Book book);

    @Transactional
    @Modifying
    @Query("UPDATE Cart c SET c.bookCount = c.bookCount + :count WHERE c.book = :book")
    void updateBookCount(Book book, int count);

    @Query("SELECT c FROM Cart c WHERE c.userId = :userId")
    List<Cart> findByUserId(int userId);
}
