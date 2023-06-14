package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Product;

import java.util.List;
import java.util.Map;

public class CartInfoResponse {

    private List<Product> cartList;
    private String message;
    private Map<Integer, Integer> mamberCart;

    public CartInfoResponse() {
    }

    public CartInfoResponse(List<Product> cartList, String message) {
        this.cartList = cartList;
        this.message = message;
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public void setCartList(List<Product> cartList) {
        this.cartList = cartList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
