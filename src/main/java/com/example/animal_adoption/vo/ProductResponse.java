package com.example.animal_adoption.vo;

import java.util.List;

import com.example.animal_adoption.entity.Product;

public class ProductResponse {
	
	private Product product;
	
	private String message;
	
	private List<Product> productList;
	

	public ProductResponse() {
		super();
	}
	
	public ProductResponse(String message) {
		super();
		this.message = message;
	}

	public ProductResponse(Product product, String message) {
		super();
		this.product = product;
		this.message = message;
	}
	
	
	public ProductResponse( List<Product> productList,String message) {
		super();
		this.productList = productList;
		this.message = message;
	}

	//=========================================================
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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}