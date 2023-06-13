package com.task.demo.controllers;

import com.task.demo.dto.BookAddingToCartDto;
import com.task.demo.dto.CreatingOrderDto;
import com.task.demo.exception.EmptyCartException;
import com.task.demo.exception.IncorrectUserIdException;
import com.task.demo.exception.NoBookException;
import com.task.demo.service.CartService;
import com.task.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody CreatingOrderDto bookAddingToCartDto) {
        try {
            String result = orderService.createOrder(bookAddingToCartDto);
            return ResponseEntity.ok(result);
        } catch (IncorrectUserIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EmptyCartException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
