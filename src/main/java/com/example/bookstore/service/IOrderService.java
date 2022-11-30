package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.model.Order;

import java.util.List;

public interface IOrderService {
    Order insert(OrderDTO orderDTO) throws Exception;

    List<Order> getAll();

    Order getById(long id);


    Order updateById(long id, OrderDTO orderDTO) ;

    String deleteById(long orderId, long userId);

    Order placeOrder(OrderDTO orderDTO);
}
