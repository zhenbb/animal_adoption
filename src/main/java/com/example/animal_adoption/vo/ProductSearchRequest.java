package com.example.animal_adoption.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSearchRequest {

	@JsonProperty("keyword")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}