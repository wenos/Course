package com.example.course.repository;

import com.example.course.models.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
    public Optional<ProductInOrder> findByProductId(long productId);

    public void deleteAllByProductId(long productId);

    public List<ProductInOrder> findAllByOrderId(long id);
}
