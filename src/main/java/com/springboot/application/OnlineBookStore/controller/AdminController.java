package com.springboot.application.OnlineBookStore.controller;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.dto.CategoryDTO;
import com.springboot.application.OnlineBookStore.entity.Category;
import com.springboot.application.OnlineBookStore.service.CategoryService;
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

    private CategoryService categoryService;

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

    @GetMapping("/category/list")
    public String listCategories(Model theModel)
    {
        List<CategoryDTO> theCategories = categoryService.findAll();

        theModel.addAttribute("categories", theCategories);

        return "admins/list-categories";
    }

    @GetMapping("/category/showFormForAdd")
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
            return "redirect:/admins/category/list";
        }

    }

    @GetMapping("/category/showFormForUpdate")
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
        return "redirect:/admins/category/list";
    }

    @GetMapping("/category/showBooks")
    public String showBooks(Model model, @RequestParam("categoryId") int theId)
    {
        List<BookDTO> books = categoryService.getCategoryBooks(theId);

        model.addAttribute("books", books);

        return "admins/list-books";
    }
}
