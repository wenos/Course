package com.example.course.controllers;


import com.example.course.models.*;
import com.example.course.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final PersonService personService;
    private final ProductInOrderService productInOrderService;

    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService, PersonService personService, ProductInOrderService productInOrderService, ProductService productService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.personService = personService;
        this.productInOrderService = productInOrderService;
        this.productService = productService;
    }

    @GetMapping("")
    public String orderPage(Model model, Principal principal) {
        long personId = personService.getPersonId(principal);
        List<Order> orders = (personService.role(principal).equals("ROLE_USER")) ?
                orderService.findAllByPersonId(personId) :
                orderService.findAllByStatusOrderNot("Доставлено");
        model.addAttribute("newStatus", "");
        model.addAttribute("orders", orders);
        model.addAttribute("orderId", null);
        model.addAttribute("role", personService.role(principal));
        return "orders";
    }

    @GetMapping("/{id}")
    public String orderWithId(@PathVariable long id, Model model) {

        List<ProductInOrder> productsInOrder = productInOrderService.findAllByOrderId(id);
        List<ProductInfo> productsInfo = new ArrayList<>();
        for (ProductInOrder productInOrder : productsInOrder) {
            Product product = productService.findById(productInOrder.getProductId()).orElse(null);
            if (product != null) {
                ProductInfo productInfo = new ProductInfo(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCost(),
                        product.getWeight(),
                        productInOrder.getCount()
                        );
                productsInfo.add(productInfo);
            }
        }
        long sum = 0;
        for (ProductInfo pr: productsInfo) {
            sum += (long) pr.getCost() * pr.getCount();
        }
        model.addAttribute("products", productsInfo);
        model.addAttribute("price", sum);
        model.addAttribute("orderAddress", orderService.findById(id).get().getAddress());
        model.addAttribute("id", id);

        return "order";
    }

    @Transactional
    @PostMapping("/buy")
    public String newOrder(Principal principal, long price, @RequestParam("address") String address) {
        long personId = personService.getPersonId(principal);
        Optional<List<Cart>> optionalCarts = cartService.deleteAllByUserIdAndActive(personId, true);
        List<Cart> carts = optionalCarts.get();
        Order order = new Order(LocalDate.now(), LocalDate.now().plusDays(1), personId, "Оформлен", price, address);
        orderService.saveOrder(order);
        for (Cart cart : carts) {
            ProductInOrder productInOrder = new ProductInOrder(order.getId(), cart.getProductId(), cart.getCount());
            productInOrderService.save(productInOrder);
        }
        return "redirect:/orders";
    }




    @PostMapping("/update-order")
    public String updateOrder(Principal principal, long orderId, String newStatus) {
        System.out.println(orderId + " " + newStatus);
        Order order = orderService.findById(orderId).get();
        order.setStatusOrder(newStatus);
        orderService.saveOrder(order);
        return "redirect:/orders";
    }
}
