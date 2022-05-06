package com.springboot.application.OnlineBookStore.service_interface;

import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDetailDTO;
import com.springboot.application.OnlineBookStore.entity.CheckoutInfo;

import java.util.List;
import java.util.Set;

public interface OrderService {

    public List<OrderDTO> findAll();

    public OrderDTO findById(int theId);

    public OrderDTO createOrder(CustomerDTO customer, List<CartItemDTO> cartItems, CheckoutInfo checkoutInfo);

    public Set<OrderDetailDTO> getDetails(int orderId);
}
