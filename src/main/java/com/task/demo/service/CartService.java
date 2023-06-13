package com.task.demo.service;

import com.task.demo.dto.BookAddingToCartDto;
import com.task.demo.entity.Book;
import com.task.demo.entity.Cart;
import com.task.demo.exception.NoBookException;
import com.task.demo.repository.BookRepo;
import com.task.demo.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private BookRepo bookRepo;
    public String addBook(BookAddingToCartDto bookAddingToCartDto) throws NoBookException {
        Book book = bookRepo.findById(bookAddingToCartDto.getBookId());
        if (book == null) {
            throw new NoBookException();
        }

        Cart cart = cartRepo.findCartByBook(book);
        if (cart == null) {
            cart = new Cart(bookAddingToCartDto.getId(), book, bookAddingToCartDto.getBookCount());
            cartRepo.save(cart);

            String str = ("В репозиторий добавлена новая книга, теперь книг с id: " + bookAddingToCartDto.getBookId() + " - " + bookAddingToCartDto.getBookCount() + " штук.");
            return str;
        }

        cartRepo.updateBookCount(book, bookAddingToCartDto.getBookCount());

        String str = ("Количество книг с данным id увеличено, теперь книг с id: " + bookAddingToCartDto.getBookId() + " - " + bookAddingToCartDto.getBookCount() + " штук.");
        return str;
    }
}
