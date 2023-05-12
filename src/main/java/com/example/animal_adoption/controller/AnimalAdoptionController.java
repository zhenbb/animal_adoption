package com.example.animal_adoption.controller;

import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalAdoptionController {

  @Autowired
  private AnimalAdoptionService animalAdoptionService;

  @PostMapping(value = "adoption")
  public AnimalAdoptionResponse adoption(@RequestBody AdoptionRequest adoptionRequest) {
    return animalAdoptionService.adoption(adoptionRequest);
  }
}
