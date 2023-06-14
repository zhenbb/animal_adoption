package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpSession;

public class AdoptionRequest {

  @JsonProperty("member_id")
  private String memberId;

  @JsonProperty("animal")
  private Animal animal;

  public AdoptionRequest() {
  }

  public String getMemberId() {
    return memberId;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

}