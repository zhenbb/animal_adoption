package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;

import java.util.List;

public class AnimalAdoptionResponse {

  private Animal animal;

  private List<Animal> animalList;

  private Member member;

  private String message;

  private List<String> messageList;

  public AnimalAdoptionResponse() {
  }

  public AnimalAdoptionResponse(Animal animal, Member member, String message) {
    this.animal = animal;
    this.member = member;
    this.message = message;
  }

  public AnimalAdoptionResponse(List<Animal> animalList, List<String> messageList) {
    this.animalList = animalList;
    this.messageList = messageList;
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

  public List<Animal> getAnimalList() {
    return animalList;
  }

  public void setAnimalList(List<Animal> animalList) {
    this.animalList = animalList;
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

  public List<String> getMessageList() {
    return messageList;
  }

  public void setMessageList(List<String> messagesList) {
    this.messageList = messagesList;
  }
}