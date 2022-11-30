package com.example.bookstore.repository;

import com.example.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query(value = "select * from book_store.cart where cart.cart_user_id=:userId",nativeQuery = true)
    Optional<Cart> findByUserId(Long userId);
    @Transactional
    @Modifying
    @Query(value = "delete from book_store.cart where cart.cart_id=:id",nativeQuery = true)
    void deleteById(Long id);
    @Query(value = "select cart_id from book_store.cart where cart.cart_user_id=:user",nativeQuery = true)
    Long findByCartUser(Long user);
}
