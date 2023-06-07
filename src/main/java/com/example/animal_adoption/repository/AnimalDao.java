package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDao extends JpaRepository<Animal,Integer> {
}
