package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.EligibilityReviewRequest;
import com.example.animal_adoption.vo.FavoriteRequest;
import com.example.animal_adoption.vo.ProductRequest;
import com.example.animal_adoption.vo.ProductResponse;

public interface AnimalAdoptionService {

  // 認養申請
  // 會員需先登入
  public AnimalAdoptionResponse adoption(AdoptionRequest adoptionRequest);

  // 認養資格審查
  // 審查員(管理員)需先登入
  public AnimalAdoptionResponse eligibilityReview(EligibilityReviewRequest reviewRequest);

  // 批次增加收藏，僅保存寵物資訊正確收藏，不正確寵物不保存
  // 會員需先登入
  public AnimalAdoptionResponse addFavorite(FavoriteRequest favoriteRequest);

  // 批次刪除，僅刪除寵物資訊正確收藏，不正確寵物不執行
  // 會員需先登入
  public AnimalAdoptionResponse deleteFavorite(FavoriteRequest favoriteRequest);
  
  // 新增商品 nana
  // 審查員(管理員)需先登入
  public ProductResponse addProduct(boolean isAdministrator, ProductRequest addProductRequest);
  
  // 更新商品系列 nana
  // 審查員(管理員)需先登入
  //1. 更改庫存
  public ProductResponse updateProductStock(boolean isAdministrator, int productId, int stock);
  
  //2. 更新商品名稱
  public ProductResponse updateProductName(boolean isAdministrator, int productId, String productName);
  
  //3. 更新商品價格
  public ProductResponse updateProductPrice(boolean isAdministrator, int productId, int price);
  
  //4. 更新商品分類
  public ProductResponse updateProductCategory(boolean isAdministrator, int productId, String category);
  
  //搜尋商品
  public ProductResponse searchKeyword(String keyword);
}