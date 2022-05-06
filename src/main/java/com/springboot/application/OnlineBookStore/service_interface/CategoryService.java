package com.springboot.application.OnlineBookStore.service_interface;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> findAll(int pageNum);

    public CategoryDTO findById(int theId);

    public void save(CategoryDTO theCategory);

    public void deleteById(int theId);

    public List<BookDTO> getCategoryBooks(int theId);
}
