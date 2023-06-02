package com.example.animal_adoption.vo;

import java.util.List;

import com.example.animal_adoption.entity.Animal;

public class AnimalInfoResponse {

	private List<Animal> animalList;

	private String message;

	public AnimalInfoResponse() {

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

}
