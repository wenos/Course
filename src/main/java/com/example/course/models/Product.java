package com.example.course.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "articul")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "name")
    private String name;


    @NotEmpty(message = "Описание не должно быть пустым")
    @Column(name = "description")
    private String description;


    @Min(value = 1, message = "Товары бесплатно не продаются")
    @Column(name = "cost")
    private int cost;


    @Min(value = 0, message = "Товары бесплатно не продаются")
    @Column(name = "weight")
    private float weight;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Product() {

    }

    public Product(String name, String description, int cost, int weight) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
