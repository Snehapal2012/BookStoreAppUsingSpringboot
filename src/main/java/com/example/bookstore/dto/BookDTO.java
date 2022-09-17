package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class BookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-Z]{1}[a-z0-9]{2,}$",message = "Book name should start with capital letter!")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-Z]{1}[a-z]{2,}$",message = "Author name should start with capital letter!")
    private String authorName;
    @NotNull(message = "Book Description can not be null!")
    private String bookDescription;
    @NotEmpty(message = "Book image can not be empty!")
    private String bookImg;
    @NotNull(message = "Price can not be null!")
    private long price;
    @NotNull(message = "Quantity can not be null!")
    private long quantity;
}
