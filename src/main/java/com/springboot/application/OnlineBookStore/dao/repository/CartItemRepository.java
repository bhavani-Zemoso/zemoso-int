package com.springboot.application.OnlineBookStore.dao.repository;

import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.CartItem;
import com.springboot.application.OnlineBookStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByCustomer(Customer customer);

    public CartItem findByCustomerAndBook(Customer customer, Book book);

    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.book.id = ?2 " +
            "AND c.customer.id = ?3")
    @Modifying
    public void updateQuantity(Integer quantity, Integer bookId, Integer customerId);

    @Query("DELETE FROM CartItem c WHERE c.customer.id = ?1 AND c.book.id = ?2")
    @Modifying
    public void deleteByCustomerAndBook(Integer customerId, Integer bookId);

    @Query("DELETE CartItem c WHERE c.customer.customerId = ?1")
    @Modifying
    public void deleteByCustomer(Integer customerId);
}
