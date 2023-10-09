package com.example.course.services;

import com.example.course.models.Cart;
import com.example.course.models.Product;
import com.example.course.models.ProductInfo;
import com.example.course.repository.CartRepository;
import com.example.course.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;


    private final ProductRepository productRepository;
    private final PersonService personService;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, PersonService personService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.personService = personService;
    }


    public void createCart(Cart cart) {
        Optional<Cart> cart1 = cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        cart.setActive(true);
        if (cart1.isPresent()) {
            cart.setCount(cart1.get().getCount() + cart.getCount());
            cart.setId(cart1.get().getId());
        }
        cartRepository.save(cart);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }


    public List<ProductInfo> allProductsInfo(Principal principal) {
        List<Cart> productsInCart = findAllByUserId(personService.getPersonId(principal));
        List<ProductInfo> productsInfo = new ArrayList<>();
        for (Cart productInCart : productsInCart) {
            Product product = productRepository.findById(productInCart.getProductId()).get();
            ProductInfo productInfo = new ProductInfo(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getCost(),
                    product.getWeight(),
                    productInCart.getCount(),
                    productInCart.isActive());
            productsInfo.add(productInfo);
        }
        return productsInfo;
    }

    public void deleteProductInCart(long userId, long productId) {
        cartRepository.deleteCartByUserIdAndProductId(userId, productId);
    }

    public Optional<List<Cart>> deleteAllByUserIdAndActive(long userid, boolean active) {
        return cartRepository.deleteAllByUserIdAndActive(userid, active);
    }

    public Optional<List<Cart>> deleteAllByUserId(long userId) {
        return cartRepository.deleteAllByUserId(userId);
    }


    public Cart findCartByUserIdAndProductId(long userId, long productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId).orElse(null);
    }



    public void deleteByProductId(long id) {
        cartRepository.deleteAllByProductId(id);
    }
}
