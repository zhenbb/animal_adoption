package com.example.animal_adoption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.EligibilityReviewRequest;
import com.example.animal_adoption.vo.ProductAddRequest;
import com.example.animal_adoption.vo.ProductResponse;
import com.example.animal_adoption.vo.ProductSearchRequest;
import com.example.animal_adoption.vo.ProductUpdateRequest;

@RestController
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

  
  
  @PostMapping(value = "add_product")
  public ProductResponse addProduct(@RequestBody ProductAddRequest request) {
    return animalAdoptionService.addProduct(request.isAdministrator(),request.getProductRequest());
  }
  

  @PostMapping(value = "update_product_name")
  public ProductResponse updateProductName(@RequestBody ProductUpdateRequest request) {
    return animalAdoptionService.updateProductName(request.isAdmin(),request.getProductId(),request.getProductName());
  }
  
  @PostMapping(value = "update_product_price")
  public ProductResponse updateProductPrice(@RequestBody ProductUpdateRequest request) {
    return animalAdoptionService.updateProductPrice(request.isAdmin(),request.getProductId(),request.getPrice());
  }
  
  @PostMapping(value = "update_product_category")
  public ProductResponse updateProductCategory(@RequestBody ProductUpdateRequest request) {
    return animalAdoptionService.updateProductCategory(request.isAdmin(),request.getProductId(),request.getCategory());
  }
  
  @PostMapping(value = "update_product_stock")
  public ProductResponse updateProductStock(@RequestBody ProductUpdateRequest request) {
    return animalAdoptionService.updateProductStock(request.isAdmin(),request.getProductId(),request.getStock());
  }
  
  @PostMapping(value = "search_product")
  public ProductResponse searchProduct(@RequestBody ProductSearchRequest request) {
    return animalAdoptionService.searchKeyword(request.getKeyword());
  }
  
}
