package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
