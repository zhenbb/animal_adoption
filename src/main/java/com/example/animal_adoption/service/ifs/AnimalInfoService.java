package com.example.animal_adoption.service.ifs;

import java.io.IOException;
import java.util.List;

import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.vo.AnimalInfoResponse;

public interface AnimalInfoService {

	//新增
	public AnimalInfoResponse animalAdd(List<Animal> animalList);
	
	//刪除
	public AnimalInfoResponse deleteById(int animalId);
	
	//修改
	public AnimalInfoResponse animalModify(List<Animal> animalList);

  public AnimalInfoResponse findAll();

  public AnimalInfoResponse upLordImg(String imgBase64,String sort,int id) throws IOException;

  public AnimalInfoResponse findByAnimalId(int animalId);
	
}