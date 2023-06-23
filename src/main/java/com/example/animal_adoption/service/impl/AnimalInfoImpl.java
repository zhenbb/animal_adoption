package com.example.animal_adoption.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.repository.AnimalDao;
import com.example.animal_adoption.service.ifs.AnimalInfoService;
import com.example.animal_adoption.vo.AnimalInfoResponse;

@Service
public class AnimalInfoImpl implements AnimalInfoService {

  @Autowired
  private AnimalDao animalDao;

  // 新增動物資訊(檢查是否有輸入值/動物名字是否為空null/動物品種是否為空null/動物所在城市是否為空null/存進資料庫)
  @Override
  public AnimalInfoResponse animalAdd(List<Animal> animalList) {
    if (CollectionUtils.isEmpty(animalList)) {
      return new AnimalInfoResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }
    for (Animal animal : animalList) {
      if (!StringUtils.hasText(animal.getAnimalName())) {
        return new AnimalInfoResponse(RtnCode.INPUT_NAME_EMPTY_VALUE_ERROR.getMessage());
      }
      if (!StringUtils.hasText(animal.getType())) {
        return new AnimalInfoResponse(RtnCode.INPUT_TYPE_EMPTY_VALUE_ERROR.getMessage());
      }
      if (!StringUtils.hasText(animal.getRegCity())) {
        return new AnimalInfoResponse(RtnCode.INPUT_CITY_EMPTY_VALUE_ERROR.getMessage());
      }
    }
    animalDao.saveAll(animalList);
    return new AnimalInfoResponse(RtnCode.ADD_INFO_SUCCESS.getMessage());
  }

  // 刪除動物資訊(刪除值依照流水號,若無此流水號則會回傳0)
  @Override
  public AnimalInfoResponse deleteById(int animalId) {
    int deleteAnimal = animalDao.deleteByName(animalId);
    if (deleteAnimal == 0) {
      return new AnimalInfoResponse(RtnCode.NOT_FOUND.getMessage());
    }
    return new AnimalInfoResponse(RtnCode.DELETE_SUCCESS.getMessage());
  }

  // 修改動物資訊(比對欲修改內容是否為空,是否有此流水號)
  @Override
  public AnimalInfoResponse animalModify(List<Animal> animalList) {
    List<Integer> updateAnimal = new ArrayList<>();
    if (CollectionUtils.isEmpty(animalList)) {
      return new AnimalInfoResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }
    for (Animal animal : animalList) {
      if (animal.getAnimalId() == 0) {
        return new AnimalInfoResponse(RtnCode.NOT_FOUND.getMessage());
      }
      updateAnimal.add(animal.getAnimalId());
    }

    List<Animal> res = animalDao.findAllById(updateAnimal);
    if (res.size() == 0) {
      return new AnimalInfoResponse(RtnCode.NOT_FOUND.getMessage());
    }
    List<Animal> newAnimalList = new ArrayList<>();
    for (Animal resList : res) {
      int itemInDB = resList.getAnimalId();
      for (Animal animal : animalList) {
        int updateItem = animal.getAnimalId();
        if (itemInDB == updateItem) {
          newAnimalList.add(animal);
        }
      }
    }
    animalDao.saveAll(newAnimalList);
    return new AnimalInfoResponse(RtnCode.MODIFY_SUCCESS.getMessage());
  }

  // 尋找所有動物
  @Override
  public AnimalInfoResponse findAll() {
    List<Animal> animalList = animalDao.findAll();
    return new AnimalInfoResponse(animalList, RtnCode.FIND_SUCCESS.getMessage());
  }


  // 透過ID尋找動物
  @Override
  public AnimalInfoResponse findByAnimalId(int animalId) {
    if (animalId < 1) {
      return new AnimalInfoResponse(RtnCode.NOT_FOUND.getMessage());
    }

    Animal animal = animalDao.findByAnimalId(animalId);
    if (animal == null) {
      return new AnimalInfoResponse(RtnCode.NOT_FOUND.getMessage());
    }
    return new AnimalInfoResponse(animal, RtnCode.FIND_SUCCESS.getMessage());
  }

  // 透過物種尋找動物
  public AnimalInfoResponse findBySpecies(boolean species) {
    List<Animal> animals = animalDao.findBySpecies(species);
    if (animals.size() == 0) {
      return new AnimalInfoResponse(RtnCode.NOT_FOUND.getMessage());
    }
    return new AnimalInfoResponse(animals, RtnCode.FIND_SUCCESS.getMessage());

  }

  @Override
  public AnimalInfoResponse findLastId(){
    int animalId = animalDao.findLastId();
    return new AnimalInfoResponse(animalId, RtnCode.FIND_SUCCESS.getMessage());
  }
}