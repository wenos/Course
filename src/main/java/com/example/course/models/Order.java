package com.example.course.models;


import jakarta.persistence.*;

import java.util.Date;
import java.time.LocalDate;
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date_order")
    private LocalDate date;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "person_id")
    private long personId;

    @Column(name = "status")
    private String statusOrder;


    @Column(name = "price")
    private long price;


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Order(LocalDate date, LocalDate deliveryDate, long personId, String statusOrder, long price) {
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.personId = personId;
        this.statusOrder = statusOrder;
        this.price = price;
    }

    public Order() {
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
