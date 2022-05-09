/*
package com.springboot.application.OnlineBookStore;

import com.springboot.application.OnlineBookStore.dao.repository.CartItemRepository;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.CartItem;
import com.springboot.application.OnlineBookStore.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CartItemTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem()
    {
        Book book = entityManager.find(Book.class, 2);
        Customer customer = entityManager.find(Customer.class, 2);

        CartItem newItem = new CartItem();
        newItem.setBook(book);
        newItem.setCustomer(customer);
        newItem.setQuantity(1);

        CartItem saveCartItem = cartItemRepository.save(newItem);

        assertTrue(saveCartItem.getId() > 0);
    }

    @Test
    public void testGetCartItemsByCustomer()
    {
        Customer customer = new Customer();
        customer.setCustomerId(1);

        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);

        assertEquals(1, cartItems.size());
    }
}

 */
