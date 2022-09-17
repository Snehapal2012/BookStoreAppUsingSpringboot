package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
public class LoginDTO {
    @Email(message = "Email should be in proper manner!")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()-+=])(?=.*[0-9])([a-zA-Z0-9@._-]).{8,}$",message = "Password should contain at least 1 special character, Caps letter,small letter and digit and can not be less than 8 characters")
    private String password;
}
