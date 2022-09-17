package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseCartDTO;
import com.example.bookstore.model.Cart;
import com.example.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")   //This is the global APi
public class CartController {
    //Create dependency injection for cartService class
    @Autowired
    ICartService cartService;
    //Create Api for Insert cart details in the database
    @PostMapping("/insert")
    public ResponseEntity<ResponseCartDTO> insert(@Valid @RequestBody CartDTO cartDTO){
        Cart cart=cartService.insert(cartDTO);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart details are added!",cart);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.CREATED);
    }
    //Create Api for Getting all cart details present in the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseCartDTO> getAll(){
        List<Cart> cartList=cartService.getAll();
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("All cart details are found!",cartList);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    //Create Api for Getting particular cart details which will be found by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseCartDTO> getById(@PathVariable long id){
        Optional<Cart> cart=cartService.getById(id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Searched cart details by id is found!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    //Create Api for Getting particular cart details which will be found by user id
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ResponseCartDTO> getByUserId(@PathVariable long userId){
        Optional<Cart> cart=cartService.getByUserId(userId);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Searched cart details by id is found!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    //Create Api for Deleting particular cart details which will be found by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseCartDTO> deleteById(@PathVariable long id){
        cartService.deleteById(id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Cart details is deleted!","Deleted cart id is: "+id);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.GONE);
    }
    //Create Api for Updating particular cart details which will be found by id
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseCartDTO> updateById(@Valid @RequestBody CartDTO cartDTO,@PathVariable long id){
        Cart cart=cartService.updateById(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart details is updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }
    //Create Api for Updating quantity for particular cart which will be found by id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseCartDTO> updateQuantity(@Valid @RequestBody CartDTO cartDTO,@PathVariable long id){
        Cart cart=cartService.UpdateQuantity(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart quantity is updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }

}
