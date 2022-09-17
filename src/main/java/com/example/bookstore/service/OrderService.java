package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.exception.OrderException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.OrderRepo;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;
    //Apply logic for Insert order details in the database
    @Override
    public Order insert(OrderDTO orderDTO) throws Exception{
        Optional<User> user = userRepo.findById(orderDTO.getUser());
        Optional<Book> book = bookRepo.findById(orderDTO.getBook());
        if (user.isPresent() && book.isPresent()) {
            Order order = new Order(user.get(), book.get(), orderDTO.getDate(), orderDTO.getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(),orderDTO.getCancel());
                orderRepo.save(order);
            String token=tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(user.get().getEmail(), "Order placed!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been placed successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n Order date:  "+order.getDate()+"\n Order Price:  "+order.getPrice()+"\n Order quantity:  "+order.getQuantity()+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+order.getBook()+"\n Order cancellation status:  "+order.isCancel());
                return order;
        } else {
            throw new OrderException("User id or book id did not match! Please check and try again!");
        }
    }
    //Apply logic for Getting all order details present in the database
    @Override
    public List<Order> getAll(){
        List<Order> orderList=orderRepo.findAll();
        return orderList;
    }
    //Apply logic for Getting particular order details which will be found by id
    @Override
    public Order getById(long id){
        Optional<Order> order=orderRepo.findById(id);
        if(order.isPresent()){
            Order order1=orderRepo.findById(id).get();
            return order1;
        }else {
            throw new OrderException("Sorry! We can not find order id: "+id);
        }
    }
    //Apply logic for Deleting particular order details which will be found by id
    @Override
    public String deleteById(long orderId, long userId){
        Optional<Order> order=orderRepo.findById(orderId);
        Optional<User> user = userRepo.findById(userId);
        if(order.isPresent() && user.isPresent()){
            orderRepo.deleteById(orderId);
            emailSenderService.sendEmail(user.get().getEmail(), "Order is deleted!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been deleted successfully! Order id: "+order.get().getOrderId());
            return "Details has been deleted!";
        }else {
            throw new OrderException("Sorry! We can not find order id: "+orderId);
        }
    }
    //Apply logic for Updating particular order which will be found by id
    @Override
    public Order updateById(long id, OrderDTO orderDTO){
        Optional<User> user=userRepo.findById(orderDTO.getUser());
        Optional<Book> book=bookRepo.findById(orderDTO.getBook());
        Order order=orderRepo.findById(id).get();
        if (orderRepo.findById(id).isPresent() && user.isPresent() && book.isPresent()){
                order.setDate(orderDTO.getDate());
                order.setPrice(orderDTO.getPrice());
                order.setQuantity(orderDTO.getQuantity());
                order.setAddress(orderDTO.getAddress());
                order.setUser(user.get());
                order.setBook(book.get());
                order.setCancel(orderDTO.getCancel());
                orderRepo.save(order);
            String token=tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(user.get().getEmail(), "Order is updated!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been updated successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n Order date:  "+order.getDate()+"\n Order Price:  "+order.getPrice()+"\n Order quantity:  "+order.getQuantity()+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+order.getBook()+"\n Order cancellation status:  "+order.isCancel());
                return order;
            }else {
            throw new OrderException("Order id, user id or book id did not match! Please check and try again!");
        }
    }

}
