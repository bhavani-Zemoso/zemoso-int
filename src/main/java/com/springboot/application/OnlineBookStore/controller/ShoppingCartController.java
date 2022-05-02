package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.entity.CartItem;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.service.CustomerService;
import com.springboot.application.OnlineBookStore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/showCart")
    public String showShoppingCart(Model model)
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();
        System.out.println("Customer : " + customer);

        List<CartItemDTO> cartItems = shoppingCartService.listCartItems(customer);
        System.out.println(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "customers/shopping-cart";
    }
}
