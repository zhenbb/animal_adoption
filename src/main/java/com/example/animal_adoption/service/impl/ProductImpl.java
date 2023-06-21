package com.example.animal_adoption.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.service.ifs.ProductService;
import com.example.animal_adoption.vo.ProductResponse;

@Service
public class ProductImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	// 新增商品 nana
	@Override
	public ProductResponse addProduct(String productName, String category, int price , int stock) {
		
		// 防呆 null/空白
		if ( !StringUtils.hasText(productName)
				|| !StringUtils.hasText(category)) {
			return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
		}
		// 數字錯誤
		if (price <= 0 || stock < 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}
			
		//創建新商品 + 用0產生流水號
		Product product = new Product(0, productName, category, price, stock);
		productDao.save(product);
		return new ProductResponse(product, RtnCode.PRODUCT_ADD_SUCCESS.getMessage());
	}

	
	
	// 1. 更新商品庫存
	@Override
	public ProductResponse updateProductStock(int productId, int stock) {
		// 防呆
		if (productId <= 0 || stock < 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}

		Optional<Product> resultOp = productDao.findById(productId);
		// 確認存在
		if (!resultOp.isPresent()) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		Product result = resultOp.get();
		result.setStock(stock);
		productDao.save(result);
		return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
	}

	// 2. 更新商品名稱
	@Override
	public ProductResponse updateProductName(int productId, String productName) {
		// 防呆
		if (productId <= 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}
		if (!StringUtils.hasText(productName)) {
			return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
		}
		Optional<Product> resultOp = productDao.findById(productId);
		// 確認存在
		if (!resultOp.isPresent()) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		Product result = resultOp.get();
		result.setProductName(productName);
		productDao.save(result);
		return new ProductResponse(result, RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
	}

	// 3. 更新商品價格
	@Override
	public ProductResponse updateProductPrice(int productId, int price) {
		// 防呆
		if (productId <= 0 || price < 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}

		Optional<Product> resultOp = productDao.findById(productId);
		// 確認存在
		if (!resultOp.isPresent()) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		Product result = resultOp.get();
		result.setPrice(price);
		productDao.save(result);
		return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
	}

	// 4. 更新商品分類
	@Override
	public ProductResponse updateProductCategory(int productId, String category) {
		// 防呆
		if (productId < 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}
		if (!StringUtils.hasText(category)) {
			return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
		}

		
		// 搜尋是否有這個商品
		Optional<Product> resultOp = productDao.findById(productId);
		// 確認存在
		if (!resultOp.isPresent()) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		Product result = resultOp.get();

		
		
		// 檢查分類是否沒修改
		// input分類，狀況預想： A,B,C / A,,C / A,B,C, / A, B, C
		String cate[] = category.replaceAll(" ", "").split(","); //清掉空白
		
		List<String> bookCateList = new ArrayList<>();
	    for (String c : cate) {
	        if (!c.isEmpty()) { // 過濾空值
	        	bookCateList.add(category);
	        }
	    }
	    	    
		// 資料庫裡的分類
		String opCate = result.getCategory();
		List<String> opCateList = Arrays.asList(opCate.split(",")); 
		
		// 針對新分類 轉換為不重複的Set
		Set<String> bookCateSet = new LinkedHashSet<>(bookCateList);

		
		
		
		// 比較List & Set長度，只要不同就直接上傳
		if (opCateList.size() != bookCateSet.size()) {
			String newCate = String.join(",", bookCateSet);
			result.setCategory(newCate);
			productDao.save(result);
			return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
		}

		
		
		// 同樣長度的[]-->比較內容是否完全一樣
		int count = 0;
		for (String bookArr : bookCateSet) {
			if (opCateList.contains(bookArr)) {
				count++;
				continue;
			}
		}
		
		// 假如新的分類完全等於原始的分類 => 錯誤訊息"無項目變更"
		if (count == opCateList.size()) {
			return new ProductResponse(RtnCode.PRODUCT_NO_CHANGE.getMessage());
		}

		// 完成後上傳
		String newCate = String.join(",", bookCateSet);
		result.setCategory(newCate);
		productDao.save(result);
		return new ProductResponse(result, RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
	}

	
	
	
	//搜尋關鍵字
	@Override
	public ProductResponse searchByNameAndCategories(String strName, String strCate, String strCate2){
		// 什麼都沒輸入 => 顯示全部
		List<Product> result;
		if (!StringUtils.hasText(strName) && !StringUtils.hasText(strCate) && !StringUtils.hasText(strCate2)) {
			result = productDao.findAll();
		}

		// 搜尋功能(分類*2)
		else if(!StringUtils.hasText(strName)) {
			result = productDao.findByCategoryContainingAndCategoryContaining(strCate, strCate2);
		}
		
		else if(!StringUtils.hasText(strCate) || !StringUtils.hasText(strCate2)) {
			String newStrCate = "";
			if(!StringUtils.hasText(strCate)) {
				newStrCate = strCate2;
			}
			else {
				newStrCate = strCate;
			}
			// 搜尋功能(關鍵字*1、分類*1)
			result = productDao.findByProductNameContainingIgnoreCaseAndCategoryContaining(strName, newStrCate);
		}

		// 搜尋功能(關鍵字*1)
		else if(!StringUtils.hasText(strCate) && !StringUtils.hasText(strCate2)) {
			result = productDao.findByProductNameContainingIgnoreCase(strName);
		}

		// 搜尋功能(關鍵字*1、分類*2)
		else {
			result = productDao.findByProductNameContainingIgnoreCaseAndCategoryContainingAndCategoryContaining
			(strName, strCate, strCate2);
		}
		
		//無結果
		if (result.size() == 0) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		
		return new ProductResponse(result, RtnCode.PRODUCT_SEARCH_SUCCESS.getMessage());
	}


}
