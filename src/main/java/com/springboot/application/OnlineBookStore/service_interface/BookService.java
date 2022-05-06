package com.springboot.application.OnlineBookStore.service_interface;

import com.springboot.application.OnlineBookStore.dto.BookDTO;
import com.springboot.application.OnlineBookStore.entity.Book;
import com.springboot.application.OnlineBookStore.entity.Category;

import java.util.List;

public interface BookService {

    public List<BookDTO> findAll(int pageNum);

    public BookDTO findById(int theId);

    public void save(BookDTO theBook);

    public void deleteById(int theId);

    public List<BookDTO> search(String keyword);
}
