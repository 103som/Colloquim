package com.task.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.task.demo.dto.BookAddingDto;
import com.task.demo.dto.BookAddingToStorageDto;
import com.task.demo.dto.BookGettingDto;
import com.task.demo.entity.Book;
import com.task.demo.entity.Storage;
import com.task.demo.entity.StorageBooks;
import com.task.demo.exception.BookAlreadyExists;
import com.task.demo.exception.BookDoesntExistException;
import com.task.demo.exception.NoBookException;
import com.task.demo.exception.NoBooksException;
import com.task.demo.repository.BookRepo;
import com.task.demo.repository.StorageBooksRepo;
import com.task.demo.repository.StorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private StorageBooksRepo storageBooksRepo;

    @Autowired
    private StorageRepo storageRepo;

    @Autowired
    private ObjectMapper mapper;
    public String getBooks() throws NoBooksException {
        List<Book> books = (List<Book>)bookRepo.findAll(); // полный список книг в каталоге.
        if (books == null) {
            throw new NoBooksException("Каталог с книгами пуст.");
        }

        ObjectNode rootNode = mapper.createObjectNode();
        for (Book book : books) {
            Integer bookCount = storageBooksRepo.findBookCountByBook(book);

            // Если книги нет в наличии на складах.
            if (bookCount == null) {
                bookCount = 0;
            }

            rootNode.putPOJO(book.getName(), bookCount);
        }

        return rootNode.toString();
    }

    public String getBookById(BookGettingDto bookGettingDto) throws  NoBookException{
        Book book = bookRepo.findById(bookGettingDto.getId()); // полный список книг в каталоге.
        if (book == null) {
            throw new NoBookException();
        }

        ObjectNode rootNode = mapper.createObjectNode();
        Integer bookCount = storageBooksRepo.findBookCountByBook(book);

        // Если книги нет в наличии на складах.
        if (bookCount == null) {
            bookCount = 0;
        }

        rootNode.putPOJO(book.toString(), bookCount);

        return rootNode.toString();
    }

    public String createBook(BookAddingDto bookAddingDto) throws  BookAlreadyExists {
        if (bookRepo.findByName(bookAddingDto.getName()) != null) {
            throw new BookAlreadyExists("Книга с данным названием уже существует.");
        }

        Book book = new Book(bookAddingDto.getName(), bookAddingDto.getAuthor(), bookAddingDto.getGenre(), bookAddingDto.getCost());
        bookRepo.save(book);

        return("Книга успешно добавлена в каталог всех книг!");
    }

    public String addBook(BookAddingToStorageDto bookAddingToStorageDto) throws BookDoesntExistException {
        Book book = bookRepo.findByName(bookAddingToStorageDto.getName());

        if (book == null) {
            throw new BookDoesntExistException("Книга с данным названием не существует в каталоге.");
        }

        Integer ind = storageRepo.findByAddress(bookAddingToStorageDto.getLocation());
        Storage storage;
        if (ind == null) {
            storage = new Storage(bookAddingToStorageDto.getLocation());
            storageRepo.save(storage);
            ind = storage.getId();
        }

        StorageBooks storageBooks = storageBooksRepo.findByBookIdAndId(ind, book);
        if (storageBooks == null) {
            storageBooks = new StorageBooks(storageRepo.findById(ind), book, bookAddingToStorageDto.getBooksCount());
            storageBooksRepo.save(storageBooks);

            return ("Данные успешно добавлены в таблицу!");
        }

        storageBooksRepo.updateBook(storageRepo.findById(ind), book, bookAddingToStorageDto.getBooksCount());
        return ("Данные о количестве книг изменены!");
    }
}
