package com.example.bookstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
public class Cart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cart_user_id")
    private User userId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cart_book_id")
    private Book bookId;
    private long quantity;

    public Cart(User user,Book book,long quantity) {
        this.userId=user;
        this.bookId=book;
        this.quantity=quantity;
    }
}
