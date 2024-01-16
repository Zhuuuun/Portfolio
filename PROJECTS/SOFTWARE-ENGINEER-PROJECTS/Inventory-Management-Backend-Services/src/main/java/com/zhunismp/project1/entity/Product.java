package com.zhunismp.project1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotNull(message = "product name is mandatory")
    @NotBlank(message = "product name is mandatory")
    private String name;
    @Column(name = "size")
    @NotNull(message = "size is mandatory")
    @NotBlank(message = "size is mandatory")
    private String size;
    @Column(name = "amount")
    @Min(value = 0,message = "negative number isn't allowed")
    private int amount;
    @Column(name = "price")
    @Min(value = 0, message = "negative number isn't allowed")
    private float price;
    @OneToMany(mappedBy = "product",
                fetch = FetchType.LAZY,
                cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Order> orders;

    public Product() {}

    public Product(String name, String size, int amount, float price) {
        this.name = name;
        this.size = size;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
