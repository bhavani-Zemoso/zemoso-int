package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.conversions.EntityDTOConversions;
import com.springboot.application.OnlineBookStore.dao.CustomerRepository;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDTO;
import com.springboot.application.OnlineBookStore.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    EntityDTOConversions entityDTOConversions;
    @Autowired
    private UserServiceImpl userService;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDTO findById(int theId) {

        Optional<Customer> result = customerRepository.findById(theId);

        Customer theCustomer = null;

        if(result.isPresent())
            theCustomer = result.get();
        else
            throw new RuntimeException("Did not find customer id - " + theId);

        return entityDTOConversions.convertFromEntityToDTO(theCustomer);
    }

    @Override
    public void save(CustomerDTO theCustomer) {

        customerRepository.save(entityDTOConversions.convertFromDTOToEntity(theCustomer));

    }

    @Override
    public void deleteById(int theId) {

        customerRepository.deleteById(theId);

    }

    @Override
    public CustomerDTO getCurrentlyLoggedInCustomer() {

        Customer customer = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        System.out.println(currentPrincipalName);

        customer = userService.getCustomerFromUsername(currentPrincipalName);

        return entityDTOConversions.convertFromEntityToDTO(customer);
    }

    @Override
    public List<OrderDTO> getCustomerOrders(int theId) {
        CustomerDTO customer = findById(theId);

        List<Order> customerOrders = customer.getOrders();

        return customerOrders.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }
}
