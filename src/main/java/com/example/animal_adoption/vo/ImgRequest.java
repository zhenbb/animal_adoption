package com.example.animal_adoption.vo;

public class ImgRequest {

  private String imgBase64;

  private String sort;

  private int id;

  public ImgRequest() {
  }

  public String getImgBase64() {
    return imgBase64;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
