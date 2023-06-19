package com.example.animal_adoption.vo;

import java.util.List;

import com.example.animal_adoption.entity.Animal;

public class AnimalInfoResponse {

  private Animal animal;

	private List<Animal> animalList;

	private String message;

  private int animalId;

	public AnimalInfoResponse() {

	}

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public AnimalInfoResponse(Animal animal, String message) {
    this.animal = animal;
    this.message = message;
  }

  public AnimalInfoResponse(String message) {
		super();
		this.message = message;
	}

	public AnimalInfoResponse(List<Animal> animalList, String message) {
		super();
		this.animalList = animalList;
		this.message = message;
	}

  public AnimalInfoResponse(int animalId,String message) {
    this.message = message;
    this.animalId = animalId;
  }

  public List<Animal> getAnimalList() {
		return animalList;
	}

	public void setAnimalList(List<Animal> animalList) {
		this.animalList = animalList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

  public int getAnimalId() {
    return animalId;
  }

  public void setAnimalId(int animalId) {
    this.animalId = animalId;
  }
}