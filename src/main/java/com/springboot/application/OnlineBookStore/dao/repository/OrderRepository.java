package com.springboot.application.OnlineBookStore.dao.repository;

import com.springboot.application.OnlineBookStore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findAll();

}
