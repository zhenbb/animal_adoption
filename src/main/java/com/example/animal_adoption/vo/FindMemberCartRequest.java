package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;

import java.util.List;
import java.util.Map;

public class FindMemberCartRequest {
    private List<Integer> productId;
    private Map<Integer, Integer> products;
    private int carId;
    private int sales;
    private int price;
    private Member member;

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
