package com.example.bookstore.dto;

import com.example.bookstore.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseBookDTO {
    private String message;
    private Object object;

    public ResponseBookDTO(String message, Book book) {
        this.message=message;
        this.object=book;
    }

    public ResponseBookDTO(String message, List<Book> bookList) {
        this.message=message;
        this.object=bookList;
    }

    public ResponseBookDTO(String message, Optional<Book> book) {
        this.message=message;
        this.object=book;
    }

    public ResponseBookDTO(String exception_while_processing_rest_request, String message) {
        this.message=exception_while_processing_rest_request;
        this.object=message;
    }
}
