package com.example.bookstore.dto;

import com.example.bookstore.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseUserDTO {
    private String message;
    private Object object;
    public ResponseUserDTO(String message, User user) {
        this.message=message;
        this.object=user;
    }

    public ResponseUserDTO(String message, List<User> user) {
        this.message=message;
        this.object=user;
    }

    public ResponseUserDTO(String exception_while_processing_rest_request, String message) {
        this.message=exception_while_processing_rest_request;
        this.object=message;
    }

    public ResponseUserDTO(String message, Optional<User> user) {
        this.message=message;
        this.object=user;
    }

}
