package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;

import java.util.Map;

public class CheckOutRequst {

    private int carId;
    private int productId;
    private int price;
    private int stock;
    private Map<Integer, Integer> carMap;
    private Member member;

    public CheckOutRequst() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Map<Integer, Integer> getCarMap() {
        return carMap;
    }

    public void setCarMap(Map<Integer, Integer> carMap) {
        this.carMap = carMap;
    }

    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
}