package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpSession;
import java.util.List;

public class FavoriteRequest {

  @JsonProperty("member")
  private Member member;

  @JsonProperty("animal")
  private List<Animal> animal;

  @JsonProperty()
  private HttpSession httpSession;
  public FavoriteRequest() {
  }

  public Member getMember() {
    return member;
  }


  public List<Animal> getAnimal() {
    return animal;
  }

  public HttpSession getHttpSession() {
    return httpSession;
  }
}