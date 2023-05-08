package com.example.course.controllers;


import com.example.course.services.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductInOrderController {
    private final ProductInOrderService productInOrderService;
    @Autowired
    public ProductInOrderController(ProductInOrderService productInOrderService) {
        this.productInOrderService = productInOrderService;
    }



}
