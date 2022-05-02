package com.springboot.application.OnlineBookStore.dto;

import com.springboot.application.OnlineBookStore.entity.Order;
import com.springboot.application.OnlineBookStore.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {

    private int customerId;

    private String customerName;

    private String customerEmail;

    private String customerAddress;

    private String phoneNo;

    private String city;

    private String country;

    private String zipCode;

    private User user;

    private List<Order> orders;
}
