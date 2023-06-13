package com.example.animal_adoption.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.service.ifs.ProductService;
import com.example.animal_adoption.vo.ProductAddRequest;
import com.example.animal_adoption.vo.ProductResponse;
import com.example.animal_adoption.vo.ProductSearchRequest;
import com.example.animal_adoption.vo.ProductUpdateRequest;

public class ProductController {

	@Autowired
	private ProductService productService;

	// 新增商品
	@PostMapping(value = "add_product")
	public ProductResponse signUp(@RequestBody ProductAddRequest request, HttpSession httpsession) {
		String memberId = (String) httpsession.getAttribute("memberId");
		String pwd = (String) httpsession.getAttribute("pwd");

		if (!StringUtils.hasText(memberId) || !StringUtils.hasText(pwd)) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
		}

		return productService.addProduct(request);
	}

	// 更新商品名稱
	@PostMapping(value = "update_product_name")
	public ProductResponse UpdateProductName(@RequestBody ProductUpdateRequest request, HttpSession httpsession) {
		String memberId = (String) httpsession.getAttribute("memberId");
		String pwd = (String) httpsession.getAttribute("pwd");

		if (!StringUtils.hasText(memberId) || !StringUtils.hasText(pwd)) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
		}

		return productService.updateProductName(request.getProductId(), request.getProductName());
	}

	// 更新分類
	@PostMapping(value = "update_product_category")
	public ProductResponse UpdateProductCategory(@RequestBody ProductUpdateRequest request,
			HttpSession httpsession) {
		String memberId = (String) httpsession.getAttribute("memberId");
		String pwd = (String) httpsession.getAttribute("pwd");

		if (!StringUtils.hasText(memberId) || !StringUtils.hasText(pwd)) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
		}

		return productService.updateProductCategory(request.getProductId(), request.getCategory());
	}

	// 更新庫存
	@PostMapping(value = "update_product_stock")
	public ProductResponse UpdateProductStcok(@RequestBody ProductUpdateRequest request,
			HttpSession httpsession) {
		String memberId = (String) httpsession.getAttribute("memberId");
		String pwd = (String) httpsession.getAttribute("pwd");

		if (!StringUtils.hasText(memberId) || !StringUtils.hasText(pwd)) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
		}

		return productService.updateProductStock(request.getProductId(), request.getStock());
	}

	// 更新價格
	@PostMapping(value = "update_product_price")
	public ProductResponse UpdateProductPrice(@RequestBody ProductUpdateRequest request,
			HttpSession httpsession) {
		String memberId = (String) httpsession.getAttribute("memberId");
		String pwd = (String) httpsession.getAttribute("pwd");

		if (!StringUtils.hasText(memberId) || !StringUtils.hasText(pwd)) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
		}

		return productService.updateProductPrice(request.getProductId(), request.getPrice());
	}

	@PostMapping(value = "search_product")
	public ProductResponse searchKeyword(@RequestBody ProductSearchRequest Request) {
		return productService.searchKeyword(Request.getKeyword());
	}

	//@GetMapping(value = "show_top12_new_product")
	//public ProductResponse showTop12NewProduct() {
	//	return productService.showTop12NewProduct();
	//}

}
