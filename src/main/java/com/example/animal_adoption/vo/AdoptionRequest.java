package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;

import javax.servlet.http.HttpSession;

public class AdoptionRequest {

  private Member member;

  private Animal animal;

  private HttpSession httpSession;
  public AdoptionRequest() {
  }

  public Member getMember() {
    return member;
  }

  public Animal getAnimal() {
    return animal;
  }

  public HttpSession getHttpSession() {
    return httpSession;
  }
}
