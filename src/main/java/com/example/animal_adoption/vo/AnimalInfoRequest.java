package com.example.animal_adoption.vo;

import java.util.List;

import com.example.animal_adoption.entity.Animal;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimalInfoRequest {
	
	  @JsonProperty("animalList")
	  private List< Animal> animal;
	  
	  private int animalId;
	  

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public List<Animal> getAnimal() {
		return animal;
	}

	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}

	public AnimalInfoRequest(List<Animal> animal) {
		super();
		this.animal = animal;
	}

	public AnimalInfoRequest() {
		super();

	}

}