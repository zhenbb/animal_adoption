package com.example.animal_adoption.vo;

import java.util.List;

import com.example.animal_adoption.entity.Animal;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimalInfoRequest {

  @JsonProperty("animalList")
  private List<Animal> animal;

  private int animalId;

  @JsonProperty("species")
  private boolean species;

  @JsonProperty("imgBase64")
  private String imgBase64;

  @JsonProperty("sort")
  private String sort;

  @JsonProperty("id")
  private int id;

  public AnimalInfoRequest() {
  }

  public List<Animal> getAnimal() {
    return animal;
  }

  public void setAnimal(List<Animal> animal) {
    this.animal = animal;
  }

  public int getAnimalId() {
    return animalId;
  }

  public void setAnimalId(int animalId) {
    this.animalId = animalId;
  }

  public boolean isSpecies() {
    return species;
  }

  public void setSpecies(boolean species) {
    this.species = species;
  }

  public String getImgBase64() {
    return imgBase64;
  }

  public void setImgBase64(String imgBase64) {
    this.imgBase64 = imgBase64;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}