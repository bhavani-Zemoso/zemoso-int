package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.CustomerDTO;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.entity.User;
import com.springboot.application.OnlineBookStore.service.CustomerService;
import com.springboot.application.OnlineBookStore.service.UserService;
import com.springboot.application.OnlineBookStore.user.BookStoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("bookStoreUser", new BookStoreUser());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("bookStoreUser") BookStoreUser bookStoreUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = bookStoreUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()){
            return "registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("bookStoreUser", new BookStoreUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }

        // create user account
        userService.save(bookStoreUser);

        logger.info("Successfully created user: " + userName);

        return "redirect:/register/showCustomerForm";
    }

    @GetMapping("/showCustomerForm")
    public String showCustomerForm(Model theModel)
    {
        User recentUser = userService.findRecentUser();

        Customer theCustomer = new Customer();
        theCustomer.setCustomerName(recentUser.getFirstName() + " " + recentUser.getLastName());
        theCustomer.setCustomerEmail(recentUser.getEmail());
        theCustomer.setUser(recentUser);

        theModel.addAttribute("recentUser", recentUser);
        theModel.addAttribute("customer", theCustomer);

        System.out.println(recentUser.getId());
        System.out.println(recentUser.getEmail());
        return "customer-form";
    }

    @PostMapping("/processCustomerForm")
    public String processCustomerForm(@ModelAttribute("customer") CustomerDTO theCustomer)
    {
        customerService.save(theCustomer);

        return "registration-confirmation";
    }
}
