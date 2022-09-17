package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.CartException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.CartRepo;
import com.example.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    //Create dependency injection for CartRepo class
    @Autowired
    CartRepo cartRepo;
    //Create dependency injection for UserRepo class
    @Autowired
    UserRepo userRepo;
    //Create dependency injection for bookRepo class
    @Autowired
    BookRepo bookRepo;
    //Apply logic for Insert cart details in the database
    @Override
    public Cart insert(CartDTO cartDTO){
        Optional<User> user=userRepo.findById(cartDTO.getUserId());
        Optional<Book> book=bookRepo.findById(cartDTO.getBookId());
        if(user.isPresent() && book.isPresent()){
        Cart cart=new Cart(user.get(),book.get(),cartDTO.getQuantity());
        cartRepo.save(cart);
        return cart;
        }else {
            throw new CartException("User id or Book id is not present! Please provide the correct details!");
        }
    }
    //Apply logic for Getting all cart details present in the database
    @Override
    public List<Cart> getAll(){
        List<Cart> cartList=cartRepo.findAll();
        return cartList;
    }
    //Apply logic for Getting particular cart details which will be found by id
    @Override
    public Optional<Cart> getById(long id){
        Optional<Cart> cart=cartRepo.findById(id);
        if (cart.isPresent()){
            return cart;
        }else {
            throw new CartException("Sorry! We can not find the cart id: "+id);
        }
    }
    //Apply logic for Getting particular cart details which will be found by user id
    @Override
    public Optional<Cart> getByUserId(long userId){
        Optional<Cart> cart=cartRepo.findByUserId(userId);
        if (cart.isPresent()){
            return cart;
        }else {
            throw new CartException("Sorry! We can not find the cart user id: "+userId);
        }
    }
    //Apply logic for Deleting particular cart details which will be found by id
    @Override
    public void deleteById(long id){
        Optional<Cart> cart=cartRepo.findById(id);
        if (cart.isPresent()){
            cartRepo.deleteById(id);
        }else {
            throw new CartException("Sorry! We can not find cart id: "+id);
        }
    }
    //Apply logic for Updating particular cart details which will be found by id
    @Override
    public Cart updateById(CartDTO cartDTO,long id){
        Optional<User> user=userRepo.findById(cartDTO.getUserId());
        Optional<Book> book=bookRepo.findById(cartDTO.getBookId());
        Cart cart=cartRepo.findById(id).get();
        if(cartRepo.findById(id).isPresent() && book.isPresent() && user.isPresent()){
            cart.setUserId(user.get());
            cart.setBookId(book.get());
            cart.setQuantity(cartDTO.getQuantity());
            cartRepo.save(cart);
            return cart;
        }else {
            throw new CartException("Sorry! Your details are incorrect! Please check!");
        }
    }
    //Apply logic for Updating quantity for particular cart which will be found by id
    @Override
    public Cart UpdateQuantity(CartDTO cartDTO, long id){
        Cart cart=cartRepo.findById(id).get();
        if(cartRepo.findById(id).isPresent()){
            cart.setQuantity(cartDTO.getQuantity());
            cartRepo.save(cart);
            return cart;
        }else {
            throw new CartException("Sorry! We can not find cart id: "+id);
        }
    }
}
