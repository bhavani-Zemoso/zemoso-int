package com.springboot.application.OnlineBookStore.dao.repository;

import com.springboot.application.OnlineBookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM book WHERE " +
            "MATCH(book_name, author, isbn) " +
            "AGAINST (?1)",
    nativeQuery = true)
    public List<Book> search(String keyword);
}
