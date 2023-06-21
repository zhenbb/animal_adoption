package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.ProductResponse;
  
public interface ProductService {
	// 新增商品 nana
	public ProductResponse addProduct(String productName, String category, int price, int stock);

	// 1. 更新商品庫存
	public ProductResponse updateProductStock(int productId, int stock);

	// 2. 更新商品名稱
	public ProductResponse updateProductName(int productId, String productName);

	// 3. 更新商品價格
	public ProductResponse updateProductPrice(int productId, int price);

	// 4. 更新商品分類
	public ProductResponse updateProductCategory(int productId, String category); 

	//搜尋功能(可多關鍵字、可用空格區分)@Query+regexp
//	public ProductResponse searchKeyword(String keyword);
	
	//搜尋功能(三種搜尋)
	public ProductResponse searchByNameAndCategories(String strName, String strCate, String strCate2);

	

}
