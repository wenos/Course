package com.example.course.controllers;

import com.example.course.models.Cart;
import com.example.course.models.ProductInfo;
import com.example.course.services.CartService;
import com.example.course.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {


    private final PersonService personService;
    private final CartService cartService;
    public CartController(PersonService personService, CartService cartService) {
        this.personService = personService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String showProducts(Model model, Principal principal) {
        List<ProductInfo>  productInfo = cartService.allProductsInfo(principal);
        long sum = 0;
        for (ProductInfo pr: productInfo) {
            if (pr.isActive())
                sum += (long) pr.getCost() * pr.getCount();
            pr.setOverPrice((long) pr.getCost() * pr.getCount());
        }
        model.addAttribute("products", productInfo);
        model.addAttribute("prodId", 0);
        model.addAttribute("price", sum);
        model.addAttribute("role", personService.role(principal));
        return "cart";
    }
    @Transactional
    @PostMapping("/delete")
    public String deleteProductInCart(Principal principal, @RequestParam("prodId") long productId) {
        cartService.deleteProductInCart(personService.getPersonId(principal), productId);
        return "redirect:/cart";
    }
    @PostMapping("")
    public String createProductInCart(@ModelAttribute("cart") Cart cart, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/products";
        }
        cart.setUserId(personService.getPersonId(principal));
        cartService.createCart(cart);
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String editCart(@RequestParam("active") boolean active, @RequestParam("prodId") long productId, Principal principal) {
        Cart cart = cartService.findCartByUserIdAndProductId(personService.getPersonId(principal), productId);
        if (cart != null) {
            cart.setActive(active);
            cartService.save(cart);
        }
        return "redirect:/cart";
    }


}
