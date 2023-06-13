package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Animal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalDao extends JpaRepository<Animal,Integer> {
	
	@Transactional
	@Modifying
	@Query("delete from Animal where animalId = :animalId")
	public int deleteByName(
			@Param("animalId")int animalId);

  public List<Animal> findAll();

  public Animal findByAnimalId(int animalId);
}