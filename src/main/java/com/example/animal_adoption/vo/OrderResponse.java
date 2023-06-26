package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Product;

import java.util.List;

public class OrderResponse {

    private Product product;
    private String message;
    private List<OrderItem> orderItemList;

    public OrderResponse(String message) {
        this.message = message;
    }

    public OrderResponse(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }


    public OrderResponse(Product product) {
        this.product = product;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
