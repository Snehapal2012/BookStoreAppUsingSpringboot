package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    @Query(value = "select * from book_store.book where book.book_name= :name",nativeQuery = true)
    Book findByName(String name);
    @Query(value = "select * from book_store.book ORDER BY price ASC",nativeQuery = true)
    List<Book> sortingAsce();
    @Query(value = "select * from book_store.book order by price DESC",nativeQuery = true)
    List<Book> sortingDsce();
}
