package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.dto.BookStoreUserDTO;
import com.springboot.application.OnlineBookStore.dto.CategoryDTO;
import com.springboot.application.OnlineBookStore.entity.Category;
import com.springboot.application.OnlineBookStore.entity.User;
import com.springboot.application.OnlineBookStore.service_interface.CategoryService;
import com.springboot.application.OnlineBookStore.service_interface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    public AdminController(CategoryService theCategoryService)
    {
        categoryService = theCategoryService;
    }

    @GetMapping("/")
    public String welcomeAdmins()
    {
        return "admins/welcome-page";
    }

    @GetMapping("/add")
    public String showAdminForm(Model model)
    {
        model.addAttribute("admin", new BookStoreUserDTO());

        return "admins/admin-form";
    }

    @PostMapping("/processAdminForm")
    public String processAdminForm(@Valid @ModelAttribute("admin") BookStoreUserDTO user, BindingResult bindingResult, Model model)
    {

        if(bindingResult.hasErrors())
            return "admins/admin-form";

        String userName = user.getUserName();

        System.out.println("Adding admin with name = " + userName);

        User existing = userService.findByUserName(userName);
        if (existing != null){
            model.addAttribute("admin", new BookStoreUserDTO());
            model.addAttribute("registrationError", "User name already exists");

            logger.warn("User name already exists.");
            return "admins/admin-form";
        }
        else
        {
            userService.saveAdmin(user);
            logger.info("Successfully added admin = " + userName);

            return "redirect:/admins/";
        }

    }

    @GetMapping("/category/list/page/{pageNum}")
    public String listCategories(@PathVariable("pageNum") int pageNum ,Model theModel)
    {
        List<CategoryDTO> theCategories = categoryService.findAll(pageNum);

        theModel.addAttribute("categories", theCategories);

        return "admins/list-categories";
    }

    @GetMapping("/category/add")
    public String showFormForAdd(Model model)
    {
        // Create model attribute to bind form data
        Category theCategory = new Category();

        model.addAttribute("category", theCategory);

        return "admins/category-form";
    }

    @PostMapping("/category/save")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDTO theCategory, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "admins/category-form";
        }

        else
        {
            //Save the Category
            categoryService.save(theCategory);

            //Use a redirect to prevent duplicate submissions
            return "redirect:/admins/category/list/page/1";
        }

    }

    @GetMapping("/category/update")
    public String showFormForUpdate(@RequestParam("categoryId") int theId,
                                    Model theModel)
    {
        //Get the Category from the service
        CategoryDTO theCategory = categoryService.findById(theId);

        //Set Category as the model attribute to pre-populate the form
        theModel.addAttribute("category", theCategory);

        //Send over to our form
        return "admins/category-form";
    }

    @GetMapping("/category/delete")
    public String delete(@RequestParam("categoryId") int theId)
    {
        //Delete the Category
        categoryService.deleteById(theId);

        //redirect to /category/list
        return "redirect:/admins/category/list/page/1";
    }

    @GetMapping("/category/books")
    public String showBooks(Model model, @RequestParam("categoryId") int theId)
    {
        List<BookDTO> books = categoryService.getCategoryBooks(theId);

        model.addAttribute("books", books);

        return "admins/list-books";
    }
}
