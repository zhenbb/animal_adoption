package com.example.animal_adoption.vo;

import java.util.Map;

public class CartResponse {

    private int carId;

    private Map<String,Integer> carMap;

    private String message;

    public CartResponse() {
    }

    public CartResponse(String message) {
        this.message = message;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Map<String, Integer> getCarMap() {
        return carMap;
    }

    public void setCarMap(Map<String, Integer> carMap) {
        this.carMap = carMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
