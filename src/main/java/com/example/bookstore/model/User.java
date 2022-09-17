package com.example.bookstore.model;

import com.example.bookstore.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    //User table attributes
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;

    //Create Constructor
    public User(UserDTO userDTO){
        this.firstName=userDTO.getFirstName();
        this.lastName=userDTO.getLastName();
        this.email=userDTO.getEmail();
        this.address=userDTO.getAddress();
        this.dob=userDTO.getDob();
        this.password=userDTO.getPassword();
    }
}
