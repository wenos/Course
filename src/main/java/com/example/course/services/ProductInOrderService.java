package com.example.course.services;


import com.example.course.models.ProductInOrder;
import com.example.course.repository.ProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInOrderService {
    private final ProductInOrderRepository productInOrderRepository;

    @Autowired
    public ProductInOrderService(ProductInOrderRepository productInOrderRepository) {
        this.productInOrderRepository = productInOrderRepository;
    }


    public void save(ProductInOrder productInOrder) {
        productInOrderRepository.save(productInOrder);
    }

    public void deleteByProductId(long id){
        productInOrderRepository.deleteAllByProductId(id);
    }


    public List<ProductInOrder> findAllByOrderId(long id){
        return productInOrderRepository.findAllByOrderId(id);
    }



}
