package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    List<Cart> getAll();

    Optional<Cart> getById(long id);
    Optional<Cart> getByUserId(long userId);

    void deleteById(long id);

    Cart updateById(CartDTO cartDTO,long id);

    Cart UpdateQuantity(CartDTO cartDTO, long id);

    Cart insert(CartDTO cartDTO);
}
