package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.conversions.EntityDTOConversions;
import com.springboot.application.OnlineBookStore.dao.repository.CategoryRepository;
import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.dto.CategoryDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.Category;
import com.springboot.application.OnlineBookStore.service_interface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    EntityDTOConversions entityDTOConversions;

    private int CATEGORIES_PER_PAGE = 4;

    @Autowired
    public CategoryServiceImpl(CategoryRepository theCategoryRepository)
    {
        categoryRepository = theCategoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll(int pageNum) {
        List<Category> categories;
        if(pageNum == 0)
            categories = categoryRepository.findAll();
        else
        {
            Pageable pageable = PageRequest.of(pageNum-1, CATEGORIES_PER_PAGE);
            Page<Category> page = categoryRepository.findAll(pageable);

            categories = page.getContent();
        }

        return categories.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(int theId) {

        Optional<Category> result = categoryRepository.findById(theId);

        Category theCategory = null;

        if(result.isPresent())
            theCategory = result.get();
        else
            throw new RuntimeException("Did not find category id - " + theId);

        return entityDTOConversions.convertFromEntityToDTO(theCategory);
    }

    @Override
    public void save(CategoryDTO theCategory) {

        categoryRepository.save(entityDTOConversions.convertFromDTOToEntity(theCategory));
    }

    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }

    @Override
    public List<BookDTO> getCategoryBooks(int theId) {
        List<Book> books;

        CategoryDTO category = findById(theId);

        books = category.getBooks();

        return books.stream()
                .map(entityDTOConversions::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }
}
