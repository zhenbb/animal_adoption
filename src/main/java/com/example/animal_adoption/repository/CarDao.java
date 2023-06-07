package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT carMap FROM car", nativeQuery = true)
    public Car byCarMap(@Param("input") String input);

    @Query(value = "SELECT car_id FROM car", nativeQuery = true)
    public Car byCarId(@Param("input") Integer input);

}