package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

    public Order findByCheckoutMap (String checkoutMap);
}
