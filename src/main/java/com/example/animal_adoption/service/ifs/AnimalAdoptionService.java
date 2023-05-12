package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;

public interface AnimalAdoptionService {

  public AnimalAdoptionResponse adoption(AdoptionRequest adoptionRequest);
}
