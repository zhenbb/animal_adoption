package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, String> {

    @Query(value = "SELECT product_id FROM product", nativeQuery = true)
    public List<Product> byProductId(@Param("input") int input);

    public List<Product> findAllByProductId(List<Integer> List);
}
