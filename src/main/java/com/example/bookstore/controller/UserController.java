package com.example.bookstore.controller;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.ResponseUserDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.model.User;
import com.example.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")    //This is the global APi
public class UserController {
    //Create dependency injection for userService class
    @Autowired
    IUserService userService;
    //Create Api for Insert user details in the database
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> register(@Valid @RequestBody UserDTO userDTO) throws Exception{
        User user=userService.register(userDTO);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User details is submitted!",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }
    //Create Api for Getting all user details present in the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUserDTO> getAll(){
        List<User> user=userService.getAll();
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("All user details are found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    //Create Api for Getting particular user details which will be found by id
    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<ResponseUserDTO> getByUserId(@PathVariable long id){
        Optional<User> user=userService.getByUserId(id);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Searched user details by id is found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    //Create Api for Getting particular user details which will be found by email
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<ResponseUserDTO> getByEmail(@PathVariable String email){
        User users=userService.getByEmail(email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Searched user by email id is found!",users);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    //Create Api for Changing or updating password using id
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseUserDTO> forgotPassword(@Valid @RequestBody UserDTO userDTO, @PathVariable String email){
        User user=userService.forgotPassword(userDTO,email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Password has been changed successfully!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.ACCEPTED);
    }
    //Create Api for Updating user details by email id
    @PutMapping("/updateUserByEmail/{email}")
    public ResponseEntity<ResponseUserDTO> updateUserByEmail(@Valid @RequestBody UserDTO userDTO, @PathVariable String email){
        User user=userService.updateUserByEmail(userDTO,email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Searched user details has been updated successfully!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    //Create Api for Check user is logged in with the correct email and password or not
    @GetMapping("/loginCheck")
    public ResponseEntity<ResponseUserDTO> loginCheck(@RequestParam(value = "email",defaultValue = "")String email, @RequestParam(value = "password",defaultValue = "")String password){
        String result=userService.loginCheck(email,password);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Congratulations!! You have successfully logged in!","Welcome...!!");
        return new ResponseEntity<>(responseUserDTO,HttpStatus.ACCEPTED);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseUserDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        String result=userService.login(loginDTO);
        ResponseUserDTO responseUserDTO=new ResponseUserDTO("Congratulations!! You have successfully logged in!",result);
        return  new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}/{token}")
    public ResponseEntity<ResponseUserDTO> deleteById(@PathVariable long id,@PathVariable String token){
        userService.deleteById(id,token);
        ResponseUserDTO responseUserDTO=new ResponseUserDTO("User details has been deleted!","Deleted user id is: "+id);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.GONE);
    }
}
