package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String welcomeCustomers(Model theModel)
    {
        List<BookDTO> theBooks = bookService.findAll();

        theModel.addAttribute("books", theBooks);

        return "customers/list-books";
    }

}
