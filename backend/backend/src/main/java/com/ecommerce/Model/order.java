package com.ecommerce.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import  java.util.List;

@Entity
@Table(name = "`order`")
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private user user;

    private LocalDateTime orderDate;

    private double totalAmount;

    private String status;

    @OneToMany(mappedBy = "order")
    private List<orderitem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<orderitem> getItems() {
        return items;
    }

    public void setItems(List<orderitem> items) {
        this.items = items;
    }
}
