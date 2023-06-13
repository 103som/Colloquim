package com.task.demo.service;

import com.task.demo.dto.BookAddingToCartDto;
import com.task.demo.dto.CreatingOrderDto;
import com.task.demo.entity.Book;
import com.task.demo.entity.Cart;
import com.task.demo.entity.Order;
import com.task.demo.entity.StorageBooks;
import com.task.demo.exception.EmptyCartException;
import com.task.demo.exception.IncorrectUserIdException;
import com.task.demo.exception.NoBookException;
import com.task.demo.repository.*;
import jakarta.persistence.Embeddable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private StorageBooksRepo storageBooksRepo;

    public String createOrder(CreatingOrderDto creatingOrderDto) throws IncorrectUserIdException, EmptyCartException {
        Integer userId = creatingOrderDto.getId();
        if (userId == null || userId < 0) {
            throw new IncorrectUserIdException();
        }

        List<Cart> carts = cartRepo.findByUserId(userId);
        if (cartRepo == null) {
            throw new EmptyCartException();
        }

        for (Cart cart : carts) {

        }

        // Добавление всех заказов в корзину.
        for (Cart cart : carts) {
            Order order = new Order(cart.getUserId(), cart.getBook(), cart.getBookCount());
            orderRepo.save(order);
        }

        // Уменьшаем количество товара на складах.
        for (Cart cart : carts) {
            StorageBooks storageBooks = storageBooksRepo.findByBook(cart.getBook());
            Integer bookCount = storageBooks.getBookCount();
            if (bookCount < cart.getBookCount()) {
                cart.setBookCount(bookCount);
            }


        }

        return null;
        /*
        cartRepo.updateBookCount(book, bookAddingToCartDto.getBookCount());

        String str = ("Количество книг с данным id увеличено, теперь книг с id: " + bookAddingToCartDto.getBookId() + " - " + bookAddingToCartDto.getBookCount() + " штук.");
        return str;
        */
    }
}
