package com.example.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BookStoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookStoreApplication.class, args);
        System.out.println("......................Welcome to Book Store Project........................!");
        System.out.println("----------------------------------------------------------------------------");
        log.info("!!!.............Hello Logger...........!!!");
    }

}
