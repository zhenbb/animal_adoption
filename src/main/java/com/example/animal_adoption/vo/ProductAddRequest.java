package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAddRequest {

	@JsonProperty("is_admin")
	private boolean isAdministrator;

	@JsonProperty("product")
	private Product product; 


}
