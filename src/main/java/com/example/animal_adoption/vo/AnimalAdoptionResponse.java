package com.example.animal_adoption.vo;

public class AnimalAdoptionResponse {

  private String message;

  public AnimalAdoptionResponse() {
  }

  public AnimalAdoptionResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
