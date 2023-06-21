package com.example.animal_adoption.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductUpdateRequest {


	@JsonProperty("product_id")
	private int productId;
	
	@JsonProperty("product_name")
	private String productName;
	
	@JsonProperty("price")
	private int price;

	@JsonProperty("category")
	private String category;

	@JsonProperty("stock")
	private int stock;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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