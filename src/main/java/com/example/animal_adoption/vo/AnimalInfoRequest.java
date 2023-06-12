package com.example.animal_adoption.vo;

import java.util.List;

import com.example.animal_adoption.entity.Animal;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimalInfoRequest {

  @JsonProperty("animalList")
  private List<Animal> animal;

  private int animalId;

  @JsonProperty("imgBase64")
  private String imgBase64;

  @JsonProperty("sort")
  private String sort;

  @JsonProperty("id")
  private int id;

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

  public String getImg() {
    return imgBase64;
  }

  public void setImg(String img) {
    this.imgBase64 = imgBase64;
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

  public AnimalInfoRequest(List<Animal> animal) {
    super();
    this.animal = animal;
  }

  public AnimalInfoRequest() {
    super();

  }

}