package com.example.animal_adoption.controller;

import com.example.animal_adoption.vo.FavoriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.EligibilityReviewRequest;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class AnimalAdoptionController {

  @Autowired
  private AnimalAdoptionService animalAdoptionService;
  
  @PostMapping(value = "adoption")
  public AnimalAdoptionResponse adoption(@RequestBody AdoptionRequest adoptionRequest) {
    return animalAdoptionService.adoption(adoptionRequest);
  }

  @PostMapping(value = "eligibility_review")
  public AnimalAdoptionResponse eligibilityReview(@RequestBody EligibilityReviewRequest reviewRequest) {
    return animalAdoptionService.eligibilityReview(reviewRequest);
  }

  @PostMapping(value = "add_favorite")
  public AnimalAdoptionResponse addFavorite(@RequestBody FavoriteRequest favoriteRequest) {
    return animalAdoptionService.addFavorite(favoriteRequest);
  }

  @PostMapping(value = "delete_favorite")
  public AnimalAdoptionResponse deleteFavorite(@RequestBody FavoriteRequest favoriteRequest) {
    return animalAdoptionService.deleteFavorite(favoriteRequest);
  }

}