package com.springboot.application.OnlineBookStore.dto;

import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.entity.OrderDetail;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {

    private int orderId;

    private Date orderDate;

    private double bookCost;

    private double subtotal;

    private double total;

    private String shippingAddress;

    private String city;

    private String country;

    private String zipcode;

    private String recipientName;

    private String recipientPhone;

    private int deliveryDays;

    private Date deliveryDate;

    private Customer customer;

    private Set<OrderDetail> orderDetails= new HashSet<>();
}
