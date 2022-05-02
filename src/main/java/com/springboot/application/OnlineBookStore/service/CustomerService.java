package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDTO;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.entity.Order;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CustomerService {


    public List<CustomerDTO> findAll();

    public CustomerDTO findById(int theId);

    public void save(CustomerDTO theCustomer);

    public void deleteById(int theId);

    public CustomerDTO getCurrentlyLoggedInCustomer();

    public List<OrderDTO> getCustomerOrders(int theId);

}
