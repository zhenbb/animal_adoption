package com.example.animal_adoption.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkout")
public class Order {

    @Id
    @Column(name = "checkout_id")
    private int checkoutId;
    @Column(name = "checkout_map")
    private String checkoutMap;

    public int getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(int checkoutId) {
        this.checkoutId = checkoutId;
    }

    public String getCheckoutMap() {
        return checkoutMap;
    }

    public void setCheckoutMap(String checkoutMap) {
        this.checkoutMap = checkoutMap;
    }
}