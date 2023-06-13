package com.example.animal_adoption.service.ifs;

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

  // 增加收藏
  // 會員需先登入
  public AnimalAdoptionResponse addFavorite(FavoriteRequest favoriteRequest);

  // 刪除收藏
  // 會員需先登入
  public AnimalAdoptionResponse deleteFavorite(FavoriteRequest favoriteRequest);
  
}