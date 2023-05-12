package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;

public class AdoptionRequest {

  private Member member;

  private Animal animal;

  private String sessionId;

  public AdoptionRequest() {
  }

  public Member getMember() {
    return member;
  }

  public Animal getAnimal() {
    return animal;
  }

  public String getSessionId() {
    return sessionId;
  }
}
