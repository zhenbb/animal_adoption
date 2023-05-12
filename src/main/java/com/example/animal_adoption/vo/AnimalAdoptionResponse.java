package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;

public class AnimalAdoptionResponse {

  private Animal animal;

  private Member member;

  private String message;

  public AnimalAdoptionResponse() {
  }

  public AnimalAdoptionResponse(Animal animal, Member member, String message) {
    this.animal = animal;
    this.member = member;
    this.message = message;
  }

  public AnimalAdoptionResponse(String message) {
    this.message = message;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
