package com.springboot.application.OnlineBookStore.service_interface;

import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.entity.CheckoutInfo;

import java.util.List;

public interface CheckoutService {
    public CheckoutInfo prepareCheckout(List<CartItemDTO> cartItems);
}
