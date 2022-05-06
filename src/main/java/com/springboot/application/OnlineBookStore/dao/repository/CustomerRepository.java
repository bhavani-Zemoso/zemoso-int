package com.springboot.application.OnlineBookStore.dao.repository;

import com.springboot.application.OnlineBookStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
