package com.example.animal_adoption.service.ifs;

import java.util.List;

import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.EligibilityReviewRequest;
import com.example.animal_adoption.vo.FavoriteRequest;

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
  
  
  // 新增商品
  // 審查員(管理員)需先登入
  public AnimalAdoptionResponse addProduct(List<Product> product);
  
}
