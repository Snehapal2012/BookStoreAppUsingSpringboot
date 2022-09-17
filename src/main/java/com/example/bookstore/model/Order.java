package com.example.bookstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_order")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private LocalDate date;
    private long price;
    private long quantity;
    private String address;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_user_id")
    private User user;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_book_id")
    private Book book;
    private boolean cancel;

    public Order(User user, Book book, LocalDate date, long price, long quantity, String address,boolean cancel) {
        this.date=date;
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.user=user;
        this.book=book;
        this.cancel=cancel;
    }
}
