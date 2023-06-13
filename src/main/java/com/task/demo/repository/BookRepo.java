package com.task.demo.repository;

import com.task.demo.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
    Book findById(Integer id);
    Book findByName(String name);
}
