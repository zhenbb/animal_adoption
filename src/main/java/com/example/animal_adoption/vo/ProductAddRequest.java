package com.example.animal_adoption.vo;

public class ProductAddRequest {
	
	private String productName;
	
	private String category;
	
	private int price;
	
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