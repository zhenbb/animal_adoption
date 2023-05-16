package com.example.animal_adoption.constants;

public enum RtnCode {
<<<<<<< HEAD

  ADD_FAVORITE_SUCCESS("200", "新增收藏成功"),
  ADD_STUDENT_SUCCESS("200", "新增學生成功"),
  DELETE_COURSE_SUCCESS("200", "課程刪除成功"),
  DELETE_STUDENT_SUCCESS("200", "學生刪除成功"),
  DROP_COURSE_SUCCESS("200", "退選成功"),
  FIND_SUCCESS("200", "尋找成功"),
  SUBMIT_SUCCESS("200", "送審成功"),
  REVIEW_SUCCESS("200", "審核通過"),
  UPDATE_COURSE_SUCCESS("200", "更新課程成功"),
  NOT_LOGGED_IN("400", "未登錄"),
  CANNOT_DELETE_STUDENT_ERROR("400", "該學生仍有選修課程，無法刪除"),
  COURSE_CODE_ERROR("400", "輸入課程代碼錯誤"),
  COURSE_FULL_ERROR("400", "該課程已滿"),
  COURSE_NOT_FOUND_ERROR("404", "找不到該課程"),
  CREDIT_OVER_LIMIT_COURSE_ERROR("400", "學分已達上限，無法選修該課程"),
  CREDIT_OVER_LIMIT_ERROR("400", "無法加選，學分超過"),
  DUPLICATE_COURSE_ERROR("400", "課程重複"),
  DUPLICATE_COURSE_NAME_ERROR("400", "已選修同名課程"),
  DUPLICATE_COURSE_TIME_ERROR("400", "已選修同時段課程"),
  DUPLICATE_STUDENT_ID_ERROR("400", "已有相同ID學生註冊"),
  INCORRECT_INFO_ERROR("400", "資料不正確"),
  HAS_BEEN_ADOPTED_ERROR("400", "已被領養"),
  INPUT_EMPTY_VALUE_ERROR("400", "輸入值為空"),
  INPUT_NOT_ALLOWED_BLANK_ERROR("400", "輸入不得為空白"),
  NO_COURSE_FOUND_ERROR("400", "無找到課程"),
  NO_SAME_COURSE_ERROR("400", "無同名課程"),
  NOT_SELECTED_ERROR("400", "您沒有選修該門課"),
  STUDENT_NOT_EXIST_ERROR("400", "學生不存在");



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
=======
    /*常數代表不會變動的值例如 PI

     */

    SUCCESSFUL("200", "Successful"),
    CANNOT_EMPTY("400", "Is empty"),
    DATA_ERROR("400", "Error"),
    NOT_FOUND("404", "Not found"),
    CREATEDSUCCESS("201", "Created successfully");


    private String code;

    private String message;


    private RtnCode(String code, String message) {
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
>>>>>>> car
}