package com.example.course.models;

import jakarta.persistence.*;

public class ProductInfo {
    private String name;
    private String description;
    private int cost;
    private float weight;
    private int count;
    private Long productId;

    private boolean active;


    public long getOverPrice() {
        return overPrice;
    }

    public void setOverPrice(long overPrice) {
        this.overPrice = overPrice;
    }

    public ProductInfo(long id, String name, String description, int cost, float weight, int count, boolean active, long overPrice) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
        this.count = count;
        this.active = active;
        this.overPrice = overPrice;
    }
    private long overPrice;

    public ProductInfo(long id, String name, String description, int cost, float weight, int count) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
        this.count = count;
    }

    public ProductInfo(long id, String name, String description, int cost, float weight, int count, boolean active) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
        this.count = count;
        this.active = active;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public ProductInfo() {

    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return name + " " + description + " " + cost + " " + weight
                + " " + count + " " + productId;
    }
}
