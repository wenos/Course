package com.example.course.services;


import com.example.course.models.Product;
import com.example.course.models.ProductInfo;
import com.example.course.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ProductInOrderService productInOrderService;
    @Autowired
    public ProductService(ProductRepository productRepository, CartService cartService, ProductInOrderService productInOrderService) {
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.productInOrderService = productInOrderService;
    }
    @PreAuthorize("hasRole('ROLE_MAINADMIN') or hasRole('ROLE_ADMIN') ")
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @PreAuthorize("hasRole('ROLE_MAINADMIN') or hasRole('ROLE_ADMIN') ")
    public void deleteById(long id) {

        cartService.deleteByProductId(id);
        productInOrderService.deleteByProductId(id);
        productRepository.findById(id).ifPresent(product -> productRepository.deleteById(id));

    }

    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }



}