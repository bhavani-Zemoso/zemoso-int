package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.dto.CategoryDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.service.BookService;
import com.springboot.application.OnlineBookStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admins/book")
public class AdminBookController {

    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public AdminBookController(BookService theBookService)
    {
        bookService = theBookService;
    }

    @GetMapping("/list")
    public String listBooks(Model theModel)
    {
        List<BookDTO> theBooks = bookService.findAll();

        theModel.addAttribute("books", theBooks);

        return "admins/list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model)
    {
        List<CategoryDTO> listCategories = categoryService.findAll();
        // Create model attribute to bind form data
        Book theBook = new Book();

        model.addAttribute("book", theBook);
        model.addAttribute("listCategories", listCategories);

        return "admins/book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") BookDTO theBook, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "admins/book-form";

        else
        {
            //Save the book
            bookService.save(theBook);

            //Use a redirect to prevent duplicate submissions
            return "redirect:/admins/book/list";
        }
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel)
    {
        List<CategoryDTO> listCategories = categoryService.findAll();
        //Get the book from the service
        BookDTO theBook = bookService.findById(theId);

        System.out.println(theBook);

        //Set book as the model attribute to pre-populate the form
        theModel.addAttribute("book", theBook);
        theModel.addAttribute("listCategories", listCategories);

        //Send over to our form
        return "admins/book-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId)
    {
        //Delete the book
        bookService.deleteById(theId);

        //redirect to /book/list
        return "redirect:/admins/book/list";
    }

}
