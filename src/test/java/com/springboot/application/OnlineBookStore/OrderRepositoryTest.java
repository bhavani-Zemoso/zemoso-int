/*
package com.springboot.application.OnlineBookStore;

import com.springboot.application.OnlineBookStore.dao.repository.OrderRepository;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.entity.Order;
import com.springboot.application.OnlineBookStore.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewOrderWithSingleProduct()
    {
        Customer customer = entityManager.find(Customer.class, 1);
        Book book = entityManager.find(Book.class, 1);

        Order mainOrder = new Order();
        mainOrder.setCustomer(customer);
        mainOrder.setOrderDate(new Date());
        mainOrder.setBookCost(book.getPrice());
        mainOrder.copyAddressFromCustomer();

        mainOrder.setSubtotal(book.getPrice());
        mainOrder.setTotal(book.getPrice());
        mainOrder.setDeliveryDate(new Date());
        mainOrder.setDeliveryDays(1);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBook(book);
        orderDetail.setOrder(mainOrder);
        orderDetail.setBookCost(book.getPrice());
        orderDetail.setQuantity(1);
        orderDetail.setSubtotal(book.getPrice());

        mainOrder.getOrderDetails().add(orderDetail);

        Order savedOrder = orderRepository.save(mainOrder);

        assertThat(savedOrder.getOrderId()).isGreaterThan(0);
    }

    @Test
    public void testCreateMultipleProducts()
    {
        Customer customer = entityManager.find(Customer.class, 2);
        Book book1 = entityManager.find(Book.class, 2);
        Book book2 = entityManager.find(Book.class, 4);

        Order mainOrder = new Order();
        mainOrder.setCustomer(customer);
        mainOrder.setOrderDate(new Date());
        mainOrder.copyAddressFromCustomer();

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setBook(book1);
        orderDetail1.setOrder(mainOrder);
        orderDetail1.setBookCost(book1.getPrice());
        orderDetail1.setQuantity(1);
        orderDetail1.setSubtotal(book1.getPrice());

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setBook(book2);
        orderDetail2.setOrder(mainOrder);
        orderDetail2.setBookCost(book2.getPrice());
        orderDetail2.setQuantity(2);
        orderDetail2.setSubtotal(book2.getPrice() * 2);

        mainOrder.getOrderDetails().add(orderDetail1);
        mainOrder.getOrderDetails().add(orderDetail2);

        mainOrder.setSubtotal(book1.getPrice() + book2.getPrice() * 2);
        mainOrder.setTotal(book1.getPrice() + book2.getPrice() * 2);
        mainOrder.setDeliveryDate(new Date());
        mainOrder.setDeliveryDays(1);
        mainOrder.setBookCost(book1.getPrice() + book2.getPrice());

        Order savedOrder = orderRepository.save(mainOrder);

        assertThat(savedOrder.getOrderId()).isGreaterThan(0);

    }

    @Test
    public void testListOrders()
    {
        Iterable<Order> orders = orderRepository.findAll();

        List<Order> result =
                StreamSupport.stream(orders.spliterator(), false)
                        .collect(Collectors.toList());

        assertThat(result.size()).isGreaterThan(0);

        result.forEach(System.out :: println);

    }

    @Test
    public void testUpdateOrder()
    {
        Integer orderId = 4;
        Order order = orderRepository.findById(orderId).get();

        order.setDeliveryDays(4);
        order.setZipcode("765609");

        Order updateOrder = orderRepository.save(order);

        assertThat(updateOrder.getDeliveryDays()).isEqualTo(4);

    }
}

 */
