package com.example.animal_adoption.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteRequest {

  @JsonProperty("member_id")
  private String memberId;

  @JsonProperty("animal_id")
  private int animalId;

  public FavoriteRequest() {
  }

  public String getMemberId() {
    return memberId;
  }

  public int getAnimalId() {
    return animalId;
  }
}