package com.example.pironeer.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public Product product;

    @ManyToOne
    public User user;
    public String status;
    public int amount;

    protected Order(){}

    public Order(User user, Product product, String status, int amount){
        this.user = user;
        this.product = product;
        this.status = status;
        this.amount = amount;
    }

    public String getStatus() {
        return status;

    }
}
