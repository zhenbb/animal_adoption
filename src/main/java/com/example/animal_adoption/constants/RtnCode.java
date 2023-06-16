package com.example.animal_adoption.constants;

public enum RtnCode {

    //Ani_Ado_Product 產品用回應區
    PRODUCT_ADD_SUCCESS("200", "新增商品成功"),
    PRODUCT_UPDATE_SUCCESS("200", "更新成功"),
    PRODUCT_SEARCH_SUCCESS("200", "搜尋商品成功"),
    PRODUCT_NOT_ADMINISTRATOR("400", "非管理員不可管理商品"),
    PRODUCT_DATA_ERROR("400", "資料格式有誤"),
    PRODUCT_CANNOT_EMPTY("400", "商品資料不可空白"),
    PRODUCT_UNAUTHORIZED("401", "尚未輸入身分!"),
    PRODUCT_NOT_FOUND("401", "尚未輸入身分!"),
    PRODUCT_ALREADY_PRESENT("409", "重複新增"),
    PRODUCT_NO_CHANGE("409", "無項目變更"),

    // animal
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
    INPUT_NOT_ALLOWED_BLANK_ERROR("400", "輸入不得為空白"),

    //cart
    ADD_PRODUCT_SUCCESS("200","新增購物車成功"),
    MODIFY_THE_QUANTITY("200","更改數量成功"),
    ADD_ORDER_SUCCESS("200","新增訂單成功"),
    ADD_PRODUCT_ERROR("400","商品庫存不足"),
    NOT_FOUND_PRODUCT_ERROR("400","找不到商品，可能已被下架或售完"),
    NOT_FOUND_CART_ERROR("400","購物車為空"),
    OUT_OF_STOCK_ERROR("400","庫存不足"),


    //img
    COMPILE_SUCCESS("200","編譯成功");


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