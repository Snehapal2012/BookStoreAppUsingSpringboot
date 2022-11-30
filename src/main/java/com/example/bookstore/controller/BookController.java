package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseBookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")    //This is the global APi
public class BookController {
    //Create dependency injection for bookService class
    @Autowired
    IBookService bookService;
    //Create Api for Insert book details in the database
    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insert(@Valid @RequestBody BookDTO bookDTO){
        Book book=bookService.insert(bookDTO);
        ResponseBookDTO responseDTO=new ResponseBookDTO("Book details has been submitted!",book);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    //Create Api for Getting all book details present in the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllBook(){
        List<Book> bookList=bookService.getAllBook();
        ResponseBookDTO responseDTO=new ResponseBookDTO("All book details are found!",bookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    //Create Api for Getting particular book details which will be found by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> getById(@PathVariable Long id){
        Optional<Book> book=bookService.getById(id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Searched book by id is found!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.FOUND);
    }

    //Create Api for Deleting particular book details which will be found by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBookDTO> deleteById(@PathVariable Long id){
        bookService.deleteById(id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book details is deleted!","Deleted book id is: "+id);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.GONE);
    }
    //Create Api for Searching particular book details by its name
    @GetMapping("/searchByBookName/{name}")
    public ResponseEntity<ResponseBookDTO> searchByBookName(@PathVariable String name){
        Book book=bookService.searchByBookName(name);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Your Book is found....!!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.FOUND);
    }
    //Create Api for Updating particular book details which will be found by id
   @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookById(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id){
        Book book=bookService.updateBookById(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Your book details is updated!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.ACCEPTED);
    }
    //Create Api for Sorting all book details by the price in ascending order
    @GetMapping("/sortingAsce")
    public ResponseEntity<ResponseBookDTO> sortingAsce(){
        List<Book> bookList=bookService.sortingAsce();
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("All book details are showing in ascending order!",bookList);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.OK);
    }
    //Create Api for Sorting all book details by the price in descending order
    @GetMapping("/sortingDsce")
    public ResponseEntity<ResponseBookDTO> sortingDsce(){
        List<Book> bookList=bookService.sortingDsce();
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("All book details are showing in ascending order!",bookList);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.OK);
    }
    //Create Api for Updating quantity for particular book which will be found by id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseBookDTO> updateQuantity(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id){
        Book book=bookService.updateQuantity(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Your book details is updated!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.ACCEPTED);
    }
}
