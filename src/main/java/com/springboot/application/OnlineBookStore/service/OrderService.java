package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.conversions.EntityDTOConversions;
import com.springboot.application.OnlineBookStore.dao.OrderRepository;
import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDTO;
import com.springboot.application.OnlineBookStore.dto.OrderDetailDTO;
import com.springboot.application.OnlineBookStore.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityDTOConversions entityDTOConversions;

    public List<OrderDTO> findAll()
    {
        List<Order> orders = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));

        return orders.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO findById(int theId) {

        Optional<Order> result = orderRepository.findById(theId);

        Order theOrder = null;

        if(result.isPresent())
            theOrder = result.get();
        else
            throw new RuntimeException("Did not find order id - " + theId);

        return entityDTOConversions.convertFromEntityToDTO(theOrder);
    }

    public OrderDTO createOrder(CustomerDTO customer, List<CartItemDTO> cartItems, CheckoutInfo checkoutInfo)
    {
        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());
        newOrder.setCustomer(entityDTOConversions.convertFromDTOToEntity(customer));
        newOrder.setBookCost(checkoutInfo.getBookCost());
        newOrder.setSubtotal(checkoutInfo.getBookCost());
        newOrder.setTotal(checkoutInfo.getBookTotal());
        newOrder.setDeliveryDays(checkoutInfo.getDeliveryDays());
        newOrder.setDeliveryDate(checkoutInfo.getDeliveryDate());
        newOrder.copyAddressFromCustomer();

        Set<OrderDetail> orderDetails = newOrder.getOrderDetails();
        for(CartItemDTO item : cartItems)
        {
            Book book = item.getBook();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(newOrder);
            orderDetail.setBook(book);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setBookCost(book.getPrice() * item.getQuantity());
            orderDetail.setSubtotal(item.getSubtotal());

            orderDetails.add(orderDetail);
        }
        return entityDTOConversions.convertFromEntityToDTO(orderRepository.save(newOrder));
    }

    public Set<OrderDetailDTO> getDetails(int orderId)
    {
        OrderDTO order = findById(orderId);

        Set<OrderDetail> orderDetails = order.getOrderDetails();

        return orderDetails.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toSet());
    }

}
