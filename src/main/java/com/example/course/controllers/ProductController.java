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
    private final CartService cartService;
    private final PersonService personService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, CartService cartService, PersonService personService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.cartService = cartService;
        this.personService = personService;
    }
    @GetMapping("")
    public String showProducts(Model model, Principal principal) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("cart", new Cart());
        long sum = cartService.findAllByUserId(personService.getPersonId(principal)).stream().mapToLong(Cart::getCount).sum();
        model.addAttribute("cartSize", sum);
        model.addAttribute("role", personService.role(principal));
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
    public String editProducts(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productId", 0);
        return "products-edit";
    }
    @PostMapping("/delete")
    @Transactional
    public String editProducts(@RequestParam("productId") long productId) {
        productService.deleteById(productId);

        return "redirect:/products/edit";
    }

}

