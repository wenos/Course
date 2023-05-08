package com.example.course.repository;


import com.example.course.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByPersonId(long personId);

    List<Order> findAllByStatusOrderNot(String status);

}
