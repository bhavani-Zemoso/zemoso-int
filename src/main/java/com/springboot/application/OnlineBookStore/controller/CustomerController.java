package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.service_interface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String welcomeCustomers(Model model)
    {
        return welcomeCustomers(1, model);
    }

    @GetMapping("/page/{pageNum}")
    public String welcomeCustomers(@PathVariable("pageNum") int pageNum, Model theModel)
    {
        List<BookDTO> theBooks = bookService.findAll(pageNum);

        theModel.addAttribute("books", theBooks);

        return "customers/list-books";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model theModel)
    {
        List<BookDTO> theBooks = bookService.search(keyword);

        theModel.addAttribute("books", theBooks);

        return "customers/list-books";
    }

}
