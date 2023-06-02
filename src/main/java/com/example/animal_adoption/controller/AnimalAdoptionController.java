package com.example.animal_adoption.controller;

import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.service.ifs.CartService;
import com.example.animal_adoption.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalAdoptionController {

    @Autowired
    private AnimalAdoptionService animalAdoptionService;

    @Autowired
    private CartService cartService;

    @PostMapping(value = "adoption")
    public AnimalAdoptionResponse adoption(@RequestBody AdoptionRequest adoptionRequest) {
        return animalAdoptionService.adoption(adoptionRequest);
    }

    @PostMapping(value = "eligibility_review")
    public AnimalAdoptionResponse eligibilityReview(@RequestBody EligibilityReviewRequest reviewRequest) {
        return animalAdoptionService.eligibilityReview(reviewRequest);
    }

    @PostMapping(value = "add_cart")
    public CartResponse addCart(@RequestBody AddCartRequst requst) {
        return cartService.addProduct(requst);
    }

    @PostMapping(value = "checkout")
    public CartResponse checkOut(@RequestBody CheckOutRequst checkOutRequst) {
        return cartService.checkOut(checkOutRequst);
    }

}
