package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpSession;

public class AdoptionRequest {

  @JsonProperty("member")
  private Member member;

  @JsonProperty("animal")
  private Animal animal;

  public AdoptionRequest() {
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

}