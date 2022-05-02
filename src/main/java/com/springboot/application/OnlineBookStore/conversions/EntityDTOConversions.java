package com.springboot.application.OnlineBookStore.conversions;

import com.springboot.application.OnlineBookStore.dto.*;
import com.springboot.application.OnlineBookStore.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityDTOConversions {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO convertFromEntityToDTO(Category category)
    {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    public Category convertFromDTOToEntity(CategoryDTO categoryDTO)
    {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }

    public BookDTO convertFromEntityToDTO(Book book)
    {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

    public Book convertFromDTOToEntity(BookDTO bookDTO)
    {
        Book book = modelMapper.map(bookDTO, Book.class);
        return book;
    }

    public CustomerDTO convertFromEntityToDTO(Customer customer)
    {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    public Customer convertFromDTOToEntity(CustomerDTO customerDTO)
    {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customer;
    }

    public OrderDTO convertFromEntityToDTO(Order order)
    {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return orderDTO;
    }

    public Order convertFromDTOToEntity(OrderDTO orderDTO)
    {
        Order order = modelMapper.map(orderDTO, Order.class);
        return order;
    }

    public OrderDetailDTO convertFromEntityToDTO(OrderDetail orderDetail)
    {
        OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetail, OrderDetailDTO.class);
        return orderDetailDTO;
    }

    public OrderDetail convertFromDTOToEntity(OrderDetailDTO orderDetailDTO)
    {
        OrderDetail orderDetail = modelMapper.map(orderDetailDTO, OrderDetail.class);
        return orderDetail;
    }

    public CartItemDTO convertFromEntityToDTO(CartItem cartItem)
    {
        CartItemDTO cartItemDTO = modelMapper.map(cartItem, CartItemDTO.class);
        return cartItemDTO;
    }

    public CartItem convertFromDTOToEntity(CartItemDTO cartItemDTO)
    {
        CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);
        return cartItem;
    }
}
