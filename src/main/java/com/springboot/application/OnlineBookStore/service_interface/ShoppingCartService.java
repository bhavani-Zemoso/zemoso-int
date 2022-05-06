package com.springboot.application.OnlineBookStore.service_interface;

import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;

import java.util.List;

public interface ShoppingCartService {

    public List<CartItemDTO> listCartItems(CustomerDTO customer);

    public Integer addBook(Integer bookId, Integer quantity, CustomerDTO customer);

    public double updateQuantity(Integer bookId, Integer quantity, CustomerDTO
            customer);

    public void removeBook(Integer bookId, CustomerDTO customer);

    public void deleteByCustomer(CustomerDTO customer);
}
