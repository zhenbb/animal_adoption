package com.example.animal_adoption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.animal_adoption.service.ifs.AnimalInfoService;
import com.example.animal_adoption.vo.AnimalInfoRequest;
import com.example.animal_adoption.vo.AnimalInfoResponse;

@CrossOrigin
@RestController
public class AnimalInfoController {

	@Autowired
	private AnimalInfoService animalInfoService;
	
	@PostMapping(value = "animalAdd")
	  public AnimalInfoResponse animalAdd(@RequestBody AnimalInfoRequest animalInfoRequest) {
	    return animalInfoService.animalAdd(animalInfoRequest.getAnimal());
	  }
	
	@PostMapping(value = "deleteByName")
	  public AnimalInfoResponse deleteByName(@RequestBody AnimalInfoRequest animalInfoRequest) {
	    return animalInfoService.deleteByName(animalInfoRequest.getAnimalId());
	  }
	
	@PostMapping(value = "animalModify")
	  public AnimalInfoResponse animalModify(@RequestBody AnimalInfoRequest animalInfoRequest) {
	    return animalInfoService.animalModify(animalInfoRequest.getAnimal());
	  }

  @GetMapping(value = "findAll")
  public AnimalInfoResponse findAll(){
    return animalInfoService.findAll();
  }
}