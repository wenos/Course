package com.example.course.services;


import com.example.course.models.Order;
import com.example.course.repository.OrderRepository;
import com.example.course.repository.ProductInOrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAllByPersonId(long personId){
        return orderRepository.findAllByPersonId(personId);
    }



    public List<Order> findAllByStatusOrderNot(String status) {
        return orderRepository.findAllByStatusOrderNot(status);
    }

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }
}
