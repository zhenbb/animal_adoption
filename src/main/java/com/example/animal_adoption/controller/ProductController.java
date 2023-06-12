package com.example.animal_adoption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.animal_adoption.service.ifs.ProductService;
import com.example.animal_adoption.vo.ProductAddRequest;
import com.example.animal_adoption.vo.ProductResponse;
import com.example.animal_adoption.vo.ProductSearchRequest;

public class ProductController {

	@Autowired
	private ProductService productService;
	

	@PostMapping(value = "add_product")
	public ProductResponse signUp(@RequestBody ProductAddRequest Request) {
		return productService.addProduct(Request);
	}
	
	@PostMapping(value = "search_product")
	public ProductResponse searchKeyword(@RequestBody ProductSearchRequest Request) {
		return productService.searchKeyword(Request.getKeyword());
	}

	@GetMapping(value = "show_top12_new_product")
	public ProductResponse showTop12NewProduct() {
		return productService.showTop12NewProduct();
	}
	
}
