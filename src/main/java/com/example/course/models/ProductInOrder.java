package com.example.course.models;


import jakarta.persistence.*;
import org.hibernate.validator.constraints.ISBN;

@Entity
@Table(name = "products_in_orders")
public class ProductInOrder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "count")
    private int count;


    public ProductInOrder(long id, long orderId, long productId, int count) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
    }
    public ProductInOrder() {

    }


    public ProductInOrder(long orderId, long productId, int count) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
