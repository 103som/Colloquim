package com.task.demo.controllers;
import com.task.demo.dto.BookAddingDto;
import com.task.demo.dto.BookAddingToStorageDto;
import com.task.demo.dto.BookGettingDto;
import com.task.demo.exception.BookAlreadyExists;
import com.task.demo.exception.BookDoesntExistException;
import com.task.demo.exception.NoBookException;
import com.task.demo.exception.NoBooksException;
import com.task.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<String> getBooks() {
        try {
            return ResponseEntity.ok(bookService.getBooks());
        } catch (NoBooksException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка на сервере.");
        }
    }

    @GetMapping("/books/id")
    public ResponseEntity<String> getBook(@RequestBody BookGettingDto bookGettingDto) {
        try {
            return ResponseEntity.ok(bookService.getBookById(bookGettingDto));
        } catch (NoBookException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка на сервере.");
        }
    }

    // Добавление книги в список всех книг.
    @PostMapping("/book/create")
    public ResponseEntity<String> createBook(@RequestBody BookAddingDto bookAddingDto) {
        try {
            return ResponseEntity.ok(bookService.createBook(bookAddingDto));
        } catch (BookAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка на сервере.");
        }
    }

    // Добавление книги на склад.
    @PostMapping("/book/add")
    public ResponseEntity<String> addBook(@RequestBody BookAddingToStorageDto bookAddingToStorageDto) {
        try {
            return ResponseEntity.ok(bookService.addBook(bookAddingToStorageDto));
        } catch (BookDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка на сервере.");
        }
    }

}
