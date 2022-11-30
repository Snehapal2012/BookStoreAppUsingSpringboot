package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book insert(BookDTO bookDTO);

    List<Book> getAllBook();

    Optional<Book> getById(Long id);

     void deleteById(Long id);

    Book searchByBookName(String name);

    Book updateBookById(BookDTO bookDTO, Long id);

    List<Book> sortingAsce();

    List<Book> sortingDsce();

    Book updateQuantity(BookDTO bookDTO, Long id);
}
