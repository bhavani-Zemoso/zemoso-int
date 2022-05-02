package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.conversions.EntityDTOConversions;
import com.springboot.application.OnlineBookStore.dao.BookRepository;
import com.springboot.application.OnlineBookStore.dao.CartItemRepository;
import com.springboot.application.OnlineBookStore.dto.CartItemDTO;
import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.CartItem;
import com.springboot.application.OnlineBookStore.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityDTOConversions entityDTOConversions;

    public List<CartItemDTO> listCartItems(CustomerDTO customer)
    {
        List<CartItem> cartItems = cartItemRepository.findByCustomer(entityDTOConversions.convertFromDTOToEntity(customer));

        return cartItems.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }

    public Integer addBook(Integer bookId, Integer quantity, CustomerDTO customer)
    {
        Integer addedQuantity = quantity;

        Book book = bookRepository.findById(bookId).get();

        CartItem cartItem = cartItemRepository.findByCustomerAndBook(entityDTOConversions.convertFromDTOToEntity(customer), book);

        if(cartItem != null)
        {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }
        else
        {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCustomer(entityDTOConversions.convertFromDTOToEntity(customer));
            cartItem.setBook(book);
        }

        System.out.println("Adding cart item");

        cartItemRepository.save(cartItem);

        return addedQuantity;
    }

    @Transactional
    public double updateQuantity(Integer bookId, Integer quantity, CustomerDTO
            customer)
    {
        cartItemRepository.updateQuantity(quantity, bookId, customer.getCustomerId());
        Book book = bookRepository.findById(bookId).get();

        double subtotal = book.getPrice() * quantity;
        return subtotal;
    }

    @Transactional
    public void removeBook(Integer bookId, CustomerDTO customer)
    {
        cartItemRepository.deleteByCustomerAndBook(customer.getCustomerId(), bookId);
    }

    @Transactional
    public void deleteByCustomer(CustomerDTO customer) {
        cartItemRepository.deleteByCustomer(customer.getCustomerId());
    }
}
