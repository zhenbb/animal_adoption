package com.example.animal_adoption.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order {

	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "order_id")
	private int orderId;
	
	 @Column(name = "order_map")
	private String orderMap;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(String orderMap) {
		this.orderMap = orderMap;
	}
	
	
}
