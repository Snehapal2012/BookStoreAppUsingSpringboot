package com.example.bookstore.dto;

import com.example.bookstore.model.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseCartDTO {
    private String message;
    private Object object;
    public ResponseCartDTO(String exception_while_processing_rest_request, String message) {
        this.message=exception_while_processing_rest_request;
        this.object=message;
    }

    public ResponseCartDTO(String message, Cart cart) {
        this.message=message;
        this.object=cart;
    }

    public ResponseCartDTO(String message, List<Cart> cartList) {
        this.message=message;
        this.object=cartList;
    }

    public ResponseCartDTO(String message, Optional<Cart> cart) {
        this.message=message;
        this.object=cart;
    }
}
