package com.example.animal_adoption.service.impl;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.service.ifs.ProductService;
import com.example.animal_adoption.vo.ProductAddRequest;
import com.example.animal_adoption.vo.ProductResponse;

public class ProductImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	// 新增商品 nana
	@Override
	public ProductResponse addProduct(ProductAddRequest productAddRequest) {
		// 防呆 null/空白
		if (productAddRequest == null || !StringUtils.hasText(productAddRequest.getProductName())
				|| !StringUtils.hasText(productAddRequest.getCategory())) {
			return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
		}
		// 數字錯誤
		if (productAddRequest.getPrice() <= 0 || productAddRequest.getStock() < 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}

		// 確認資料庫裡有沒有
//		List<Product> resultList = productDao.findByName();
//		if(!CollectionUtils.isEmpty(resultList)) {
//			System.out.println("有重複名字的商品，是否要重複新增？");
//		}
		Product product = new Product(0, productAddRequest.getProductName(), productAddRequest.getCategory(),
				productAddRequest.getPrice(), productAddRequest.getStock());
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
		return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
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
	public ProductResponse updateProductCategory(int productId, String category) {// 防呆
		if (productId < 0) {
			return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
		}
		if (!StringUtils.hasText(category)) {
			return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
		}

		// 搜尋是否有這本書
		Optional<Product> resultOp = productDao.findById(productId);
		// 確認存在
		if (!resultOp.isPresent()) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		Product result = resultOp.get();

		// 檢查分類是否沒修改
		// 步驟1/3--抓出字串+去頭去尾+分割+加入List
		String opCate = result.getCategory().substring(1, result.getCategory().length() - 1);
		String bookCate = category.substring(1, category.length() - 1);
		List<String> opCateList = Arrays.asList(opCate.split(", "));
		List<String> bookCateList = Arrays.asList(bookCate.split(", "));

		// 針對新分類 轉換為不重複的Set
		Set<String> bookCateSet = new LinkedHashSet<>(bookCateList);

		// 步驟2/3--先比較List & Set長度，只要不同就直接上傳
		if (opCateList.size() != bookCateSet.size()) {
			result.setCategory(category);
			productDao.save(result);
			return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
		}

		// 步驟3/3--同樣長度的[]-->比較內容是否完全一樣
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
		String newCate = String.join(", ", bookCateSet);
		result.setCategory(newCate);
		productDao.save(result);
		return new ProductResponse(result, RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
	}

	//搜尋關鍵字
	@Override
	public ProductResponse searchKeyword(String keyword) {
		// 防呆
		if (!StringUtils.hasText(keyword)) {
			return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
		}
		List<Product> result = productDao.searchAllByKeywordRegexp(keyword);
		if (result.size() == 0) {
			return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
		}
		return new ProductResponse(result, RtnCode.PRODUCT_SEARCH_SUCCESS.getMessage());
	}

	
	//前端要用的功能
	//展示前12新商品
	//@Override
	//public ProductResponse showTop12NewProduct() {
	//
	//	List<Product> result = productDao.findTop12OrderByProductIdDesc();
	//
	//	if (result.size() == 0) {
	//		return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
	//	}
	//
	//	return new ProductResponse(result, RtnCode.PRODUCT_SEARCH_SUCCESS.getMessage());
  //
	//}

}
