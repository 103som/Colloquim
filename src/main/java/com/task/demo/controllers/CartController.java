package com.task.demo.controllers;

import com.task.demo.dto.BookAddingToCartDto;
import com.task.demo.exception.NoBookException;
import com.task.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<String> addBook(@RequestBody BookAddingToCartDto userDto) {
        try {
            String result = cartService.addBook(userDto);
            return ResponseEntity.ok(result);
        } catch (NoBookException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
