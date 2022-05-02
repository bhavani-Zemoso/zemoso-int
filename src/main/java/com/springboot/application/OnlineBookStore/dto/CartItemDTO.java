package com.springboot.application.OnlineBookStore.dto;

import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.Customer;
import lombok.Data;

@Data
public class CartItemDTO {

    private int id;

    private Book book;

    private Customer customer;

    private int quantity;

    private double subtotal;
}
