package com.example.animal_adoption.constants;

public enum RtnCode {

  ADD_FAVORITE_SUCCESS("200", "新增收藏成功"),
  ADD_INFO_SUCCESS("200", "新增資訊成功"),
  FIND_SUCCESS("200", "尋找成功"),
  SUBMIT_SUCCESS("200", "送審成功"),
  MODIFY_SUCCESS("200", "修改成功"),
  DELETE_SUCCESS("200", "刪除成功"),
  REVIEW_SUCCESS("200", "審核通過"),
  NOT_LOGGED_IN("400", "未登錄"),
  NOT_FOUND("400", "此資訊不存在"),
  INCORRECT_INFO_ERROR("400", "資料不正確"),
  HAS_BEEN_ADOPTED_ERROR("400", "已被領養"),
  INPUT_NAME_EMPTY_VALUE_ERROR("400", "名稱輸入值為空"),
  INPUT_TYPE_EMPTY_VALUE_ERROR("400", "品種輸入值為空"),
  INPUT_CITY_EMPTY_VALUE_ERROR("400", "登記城市輸入值為空"),
  INPUT_NOT_ALLOWED_BLANK_ERROR("400", "輸入不得為空白");




  private String code;

  private String message;

  RtnCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
