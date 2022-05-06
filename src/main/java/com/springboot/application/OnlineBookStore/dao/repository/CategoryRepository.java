package com.springboot.application.OnlineBookStore.dao.repository;

import com.springboot.application.OnlineBookStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
