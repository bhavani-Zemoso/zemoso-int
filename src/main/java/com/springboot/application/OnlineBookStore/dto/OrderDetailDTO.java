package com.springboot.application.OnlineBookStore.dto;

import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.Order;
import lombok.Data;

@Data
public class OrderDetailDTO {

    private int orderDetailId;

    private int quantity;

    private double bookCost;

    private double subtotal;

    private Book book;

    private Order order;
}
