package com.example.animal_adoption.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSearchRequest {

	@JsonProperty("keyword")
	private String keyword;

	@JsonProperty("strName")
	private String strName;

	@JsonProperty("strCate")
	private String strCate;

	@JsonProperty("strCate2")
	private String strCate2;
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrCate() {
		return strCate;
	}

	public void setStrCate(String strCate) {
		this.strCate = strCate;
	}

	public String getStrCate2() {
		return strCate2;
	}

	public void setStrCate2(String strCate2) {
		this.strCate2 = strCate2;
	}
}