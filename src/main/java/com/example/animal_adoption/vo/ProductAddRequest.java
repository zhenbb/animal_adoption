package com.example.animal_adoption.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAddRequest {
	

//	目前先用前端去判斷
//	@JsonProperty("is_admin")
//	private boolean isAdmin;

	@JsonProperty("product_name")
	private String productName;

	@JsonProperty("category")
	private String category;

	@JsonProperty("price")
	private int price;

	@JsonProperty("stock")
	private int stock;
	
	public ProductAddRequest() {
		super();
	}

	public ProductAddRequest(String productName, String category, int price, int stock) {
		super();
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}