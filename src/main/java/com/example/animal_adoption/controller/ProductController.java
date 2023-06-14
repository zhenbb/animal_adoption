package com.example.animal_adoption.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_adoption.constants.MemberRtnCode;
import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.constants.SessionCode;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.service.ifs.ProductService;
import com.example.animal_adoption.vo.ProductAddRequest;
import com.example.animal_adoption.vo.ProductResponse;
import com.example.animal_adoption.vo.ProductSearchRequest;
import com.example.animal_adoption.vo.ProductUpdateRequest;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDao productDao;

	// 新增商品
//	@CrossOrigin(value="http://127.0.0.1:5501")
	@PostMapping(value = "add_product")
	public ProductResponse addProduct(@RequestBody ProductAddRequest request, HttpSession httpSession) {
		
		if(!checkLogin(httpSession)) {
			return new ProductResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
		}
		return productService.addProduct(request);
	}

	
	
	// 更新 1. 商品名稱
	@PostMapping(value = "update_product_name")
	public ProductResponse updateProductName(@RequestBody ProductUpdateRequest request, HttpSession httpSession) {
		
		if(!checkLogin(httpSession)) {
			return new ProductResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
		}  

		return productService.updateProductName(request.getProductId(), request.getProductName());
	}

	
	// 更新 2.分類
	@PostMapping(value = "update_product_category")
	public ProductResponse updateProductCategory(@RequestBody ProductUpdateRequest request,
			HttpSession httpSession) {
		if(!checkLogin(httpSession)) {
			return new ProductResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
		}

		return productService.updateProductCategory(request.getProductId(), request.getCategory());
	}

	
	// 更新 3. 庫存
	@PostMapping(value = "update_product_stock")
	public ProductResponse updateProductStcok(@RequestBody ProductUpdateRequest request,
			HttpSession httpSession) {
		if(!checkLogin(httpSession)) {
			return new ProductResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
		}

		return productService.updateProductStock(request.getProductId(), request.getStock());
	}

	// 更新 4. 價格
	@PostMapping(value = "update_product_price")
	public ProductResponse updateProductPrice(@RequestBody ProductUpdateRequest request,
			HttpSession httpSession) {
		if(!checkLogin(httpSession)) {
			return new ProductResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
		}

		return productService.updateProductPrice(request.getProductId(), request.getPrice());
	}

	// 關鍵字搜尋
	@PostMapping(value = "search_product")
	public ProductResponse searchKeyword(@RequestBody ProductSearchRequest Request) {
		return productService.searchKeyword(Request.getKeyword());
	}
	
	//前端用
	//秀出所有商品
	@GetMapping(value="find_All")
	public ProductResponse findAll() {
		return new ProductResponse( productDao.findAll(),RtnCode.PRODUCT_SEARCH_SUCCESS.getMessage());
		
	}

	// session判斷是否有登入
	private boolean checkLogin(HttpSession httpSession) {
		String sessionMemberId = (String) httpSession.getAttribute(SessionCode.MEMBER_ID.getCode());
		String sessionPwd = (String) httpSession.getAttribute(SessionCode.MEMBER_PWD.getCode());
		
	    if (!StringUtils.hasText(sessionMemberId) || !StringUtils.hasText(sessionPwd)) {
	    	return false;
	    }
		return true;
		
	}
}
