package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDTO;
import com.springboot.application.OnlineBookStore.entity.Order;
import com.springboot.application.OnlineBookStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admins/customers")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listBooks(Model theModel)
    {
        List<CustomerDTO> theCustomers = customerService.findAll();

        theModel.addAttribute("customers", theCustomers);

        return "admins/list-customers";
    }

    @GetMapping("/showOrders")
    public String showCustomerOrders(Model theModel, @RequestParam("customerId") int customerId)
    {
        List<OrderDTO> orders = customerService.getCustomerOrders(customerId);

        theModel.addAttribute("orders", orders);

        return"admins/list-orders";
    }
}
