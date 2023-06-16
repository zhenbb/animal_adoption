package com.example.animal_adoption.vo;

public class ImgResponse {

  private int count;

  private String message;

  public ImgResponse() {
  }

  public ImgResponse(String message) {
    this.message = message;
  }

  public ImgResponse(int count, String message) {
    this.count = count;
    this.message = message;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
