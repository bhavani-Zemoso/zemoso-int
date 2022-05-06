package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDetailDTO;
import com.springboot.application.OnlineBookStore.service_interface.CustomerService;
import com.springboot.application.OnlineBookStore.service_interface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/admins/orders/list")
    public String listOrders(Model model)
    {
        List<OrderDTO> orders = orderService.findAll();

        model.addAttribute("orders", orders);

        return "admins/list-orders";
    }

    @GetMapping("/admins/orders/orderDetails")
    public String listOrderDetails(Model model, @RequestParam("orderId") int theId)
    {
        Set<OrderDetailDTO> orderDetails = orderService.getDetails(theId);

        model.addAttribute("orderDetails", orderDetails);

        return "admins/list-order-details";
    }

    @GetMapping("/customers/orders/list")
    public String listOrdersForCustomer(Model model)
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();

        List<OrderDTO> orders = customerService.getCustomerOrders(customer.getCustomerId());

        model.addAttribute("orders", orders);

        return "customers/list-orders";
    }

    @GetMapping("/customers/orders/orderDetails")
    public String listOrderDetailsForCustomer(Model model, @RequestParam("orderId") int theId)
    {
        Set<OrderDetailDTO> orderDetails = orderService.getDetails(theId);

        model.addAttribute("orderDetails", orderDetails);

        return "customers/list-order-details";
    }


}
