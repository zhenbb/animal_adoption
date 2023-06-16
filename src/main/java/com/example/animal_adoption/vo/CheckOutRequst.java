package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;

import java.util.List;
import java.util.Map;

public class CheckOutRequst {

    private int carId;
    private Integer[] productId;
    private int price;
    private Integer[] sales;

    private Map<Integer, Integer> carMap;
    private Member member;


    public CheckOutRequst() {
    }

    public Integer[] getSales() {
        return sales;
    }

    public void setSale(Integer[] sales) {
        this.sales = sales;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Integer[] getProductId() {
        return productId;
    }

    public void setProductId(Integer[] productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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