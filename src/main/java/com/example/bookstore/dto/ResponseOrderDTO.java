package com.example.bookstore.dto;

import com.example.bookstore.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseOrderDTO {
    private String message;
    private Object object;

    public ResponseOrderDTO(String message, Order order) {
        this.message=message;
        this.object=order;
    }

    public ResponseOrderDTO(String message, List<Order> orderList) {
        this.message=message;
        this.object=orderList;
    }

    public ResponseOrderDTO(String exception_while_processing_rest_request, String message) {
        this.message=exception_while_processing_rest_request;
        this.message=message;
    }
}
