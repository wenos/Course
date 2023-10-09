package com.example.course.repository;

import com.example.course.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(long userId);
    Optional<List<Cart>> deleteAllByUserIdAndActive(long userId, boolean isActive);
    Optional<List<Cart>> deleteAllByUserId(long userId);

    Optional<Cart> findCartByUserIdAndProductId(long userId, long productId);
    Optional<Cart> findByUserIdAndProductId(long userId, long productId);
    void deleteCartByUserIdAndProductId(long userId, long productId);

    public Optional<Cart> findByProductId(long productId);


    public void deleteAllByProductId(long id);

}
