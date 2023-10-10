package com.example.course.controllers;


import com.example.course.models.Cart;
import com.example.course.models.Product;
import com.example.course.repository.ProductRepository;
import com.example.course.services.CartService;
import com.example.course.services.PersonService;
import com.example.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;


    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }
    @GetMapping("")
    public String showProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("cart", new Cart());
        return "products";
    }

    @PostMapping("")
    public String createProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products-edit";
        }
        productService.createProduct(product);
        return "redirect:/products/edit";
    }
    @GetMapping("/edit")
    @PreAuthorize("hasRole('ROLE_MAINADMIN') or hasRole('ROLE_ADMIN')")
    public String editProducts(Model model, String error) {
        model.addAttribute("product", new Product());
        model.addAttribute("productId", 0);
        model.addAttribute("error", error);
        return "products-edit";
    }
    @PostMapping("/delete")
    @Transactional
    public String editProducts(@RequestParam("productId") long productId) {
        if (productService.findById(productId).isEmpty()) {
            return "redirect:/products/edit?error";
        }
        productService.deleteById(productId);
        return "redirect:/products/edit";
    }

}

