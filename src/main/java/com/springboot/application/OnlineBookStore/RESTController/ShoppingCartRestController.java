package com.springboot.application.OnlineBookStore.RESTController;

import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.service.CustomerService;
import com.springboot.application.OnlineBookStore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class
ShoppingCartRestController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("customers/cart/add/{bid}/{qty}")
    public String addProductToCart(@PathVariable("bid") Integer bookId,
                                   @PathVariable("qty") Integer quantity)
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();

        Integer addedQuantity = shoppingCartService.addBook(bookId, quantity, customer);

        System.out.println("Item added successfully");

        return addedQuantity + " items(s) of this product were added to your shopping cart.";
    }

    @PostMapping("customers/cart/update/{bid}/{qty}")
    public String updateQuantity(@PathVariable("bid") Integer bookId,
                                   @PathVariable("qty") Integer quantity)
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();

        double subtotal = shoppingCartService.updateQuantity(bookId, quantity, customer);

        System.out.println("Subtotal updated successfully");

        return String.valueOf(subtotal);
    }

    @PostMapping("customers/cart/remove/{bid}")
    public String removeBookFromCart(@PathVariable("bid") Integer bookId)
    {
        CustomerDTO customer = customerService.getCurrentlyLoggedInCustomer();

        shoppingCartService.removeBook(bookId, customer);

        System.out.println("Book has been removed successfully from shopping cart");

        return "The book has been removed successfully";
    }

}
