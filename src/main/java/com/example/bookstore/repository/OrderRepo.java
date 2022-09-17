package com.example.bookstore.repository;

import com.example.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from book_store.book_order where book_order.order_id=:orderId",nativeQuery = true)
    void deleteById(long orderId);
}
