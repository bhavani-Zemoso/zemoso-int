package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.entity.CheckoutInfo;
import com.springboot.application.OnlineBookStore.service_interface.CheckoutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Override
    public CheckoutInfo prepareCheckout(List<CartItemDTO> cartItems)
    {
        CheckoutInfo checkoutInfo = new CheckoutInfo();

        double bookCost = calculateBookCost(cartItems);
        double totalCost = calculateTotalCost(cartItems);

        checkoutInfo.setBookCost(bookCost);
        checkoutInfo.setBookTotal(totalCost);
        checkoutInfo.setDeliveryDays(3);

        return checkoutInfo;
    }

    private double calculateBookCost(List<CartItemDTO> cartItems)
    {
        double cost = 0;

        for(CartItemDTO item : cartItems)
            cost += item.getQuantity() * item.getBook().getPrice();

        return cost;
    }

    private double calculateTotalCost(List<CartItemDTO> cartItems)
    {
        double total = 0;

        for(CartItemDTO item : cartItems)
            total += item.getSubtotal();

        return total;
    }
}
