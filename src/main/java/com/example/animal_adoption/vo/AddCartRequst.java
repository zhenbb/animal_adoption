package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;

public class AddCartRequst {

    private int productId;
    private int carId;
    private int sales;
    private int price;
    private Member member;

    public int getProductId() {
        return productId;
    }

    public void setProduceId(int produceId) {
        this.productId = produceId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}