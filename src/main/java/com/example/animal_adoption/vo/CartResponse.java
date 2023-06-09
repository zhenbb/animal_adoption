package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Car;
import com.example.animal_adoption.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartResponse {

    private int carId;
    private int totalPrice;
    private Map<String,Integer> carMap;
    private String message;
    private Product product;
    private List<Product> cartList;

    public CartResponse(List<Product> cartList) {
        this.cartList = cartList;
    }

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

    public List<Product> getCartList() {
        return cartList;
    }

    public void setCartList(List<Product> cartList) {
        this.cartList = cartList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}