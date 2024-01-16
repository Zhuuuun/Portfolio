package com.zhunismp.project1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_id", nullable = false)
    @NotNull(message = "customerId is mandatory")
    @Min(value = 1 , message = "customerId must greater than 0")
    private int customerId;
    @Column(name = "product_id", nullable = false)
    @NotNull(message = "productId is mandatory")
    @Min(value = 1 , message = "productId must greater than 0")
    private int productId;
    @Column(name = "amount")
    @Min(value = 0 , message = "negative amount isn't allowed")
    private int amount;
    @Column(name = "total")
    @Min(value = 0, message = "negative total isn't allowed")
    private float total;
    @Column(name = "destination")
    @NotNull(message = "destination is mandatory")
    @NotBlank(message = "destination is mandatory")
    private String destination;
    @Column(name = "order_date")
    @NotNull(message = "orderDate is mandatory")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDateTime;

    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "status")
    private int status;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_id",insertable=false,updatable = false)
    private Product product;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "customer_id",insertable=false,updatable = false)
    private Customer customer;
    public Order() {}

    public Order(int productId, int customerId, int amount, float total, String destination, LocalDateTime orderDateTime) {
        this.productId = productId;
        this.customerId = customerId;
        this.amount = amount;
        this.total = total;
        this.destination = destination;
        this.orderDateTime = orderDateTime;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTotal() { return total; }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", total=" + total +
                ", destination='" + destination + '\'' +
                ", orderDateTime=" + orderDateTime +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}
