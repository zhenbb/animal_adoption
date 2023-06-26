package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Product;

import java.util.List;
import java.util.Map;

public class OrderItem {

    private List<Product> products; // 訂單中的商品清單
    private Map<Integer, Integer> quantities; // 每個商品的訂購數量
    private int totalPrice; // 訂單的總價格

    public OrderItem(List<Product> products, Map<Integer, Integer> quantities, int totalPrice) {
        this.products = products;
        this.quantities = quantities;
        this.totalPrice = totalPrice;
    }

    // 提供 getter 方法來取得詳細資訊


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Map<Integer, Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<Integer, Integer> quantities) {
        this.quantities = quantities;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
