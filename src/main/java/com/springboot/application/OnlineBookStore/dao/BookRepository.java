package com.springboot.application.OnlineBookStore.dao;

import com.springboot.application.OnlineBookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
