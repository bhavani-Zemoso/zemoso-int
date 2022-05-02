package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.entity.CheckoutInfo;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.service.CheckoutService;
import com.springboot.application.OnlineBookStore.service.CustomerService;
import com.springboot.application.OnlineBookStore.service.OrderService;
import com.springboot.application.OnlineBookStore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("customers/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String showCheckoutPage(Model model)
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();

        List<CartItemDTO> cartItems = shoppingCartService.listCartItems(customer);
        CheckoutInfo checkoutInfo = checkoutService.prepareCheckout(cartItems);

        model.addAttribute("checkoutInfo", checkoutInfo);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("customer", customer);

        return"customers/checkout-page";
    }

    @PostMapping("/place_order")
    public String placeOrder()
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();

        List<CartItemDTO> cartItems = shoppingCartService.listCartItems(customer);
        CheckoutInfo checkoutInfo = checkoutService.prepareCheckout(cartItems);

        orderService.createOrder(customer, cartItems, checkoutInfo);
        shoppingCartService.deleteByCustomer(customer);

        return "customers/order-confirmation";
    }

}
