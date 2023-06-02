package com.example.animal_adoption.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="order")
public class Order {

    @Id
    @Column(name = "order_id")
    private int carId;
    @Column(name = "orderMap")
    private String orderMap;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(String orderMap) {
        this.orderMap = orderMap;
    }
}
