package com.example.animal_adoption.service.impl;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.repository.AnimalDao;
import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AnimalAdoptionImpl implements AnimalAdoptionService {

  @Autowired
  HttpSession session;

  @Autowired
  AnimalDao animalDao;
  public AnimalAdoptionResponse adoption(AdoptionRequest adoptionRequest){

    // 取得客戶端Session
    HttpSession clientSession = adoptionRequest.getHttpSession();
    // 驗證客戶端Id與伺服器端Id，判斷是否有登入
    String serviceSession = (String)session.getAttribute(clientSession.getId());
    if (!StringUtils.hasText(serviceSession)){
      return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
    }

    // 取出欲領養動物資訊
    Animal animal = adoptionRequest.getAnimal();
    // 取出欲領養人資訊
    Member member = adoptionRequest.getMember();

    // 判斷資料是否不為空
    if (animalCheck(animal)||memberCheck(member)){
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領養動物是否存在
    Optional<Animal> temp = animalDao.findById(animal.getAnimalId());
    if(!temp.isPresent()){
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    Animal theAnimal = temp.get();
    // 判斷動物是否尚未被領養
    if(theAnimal.getAdoptDate()!=null){
      return new AnimalAdoptionResponse(RtnCode.HAS_BEEN_ADOPTED_ERROR.getMessage());
    }

    return new AnimalAdoptionResponse(theAnimal,member,RtnCode.SUBMIT_SUCCESS.getMessage());
  }

  // 資料不符返回true
  private boolean animalCheck(Animal animal){
    return animal.getAnimalId() < 1
            || !StringUtils.hasText(animal.getAnimalName())
            || !StringUtils.hasText(animal.getType())
            || animal.getRegDate() ==null
            || !StringUtils.hasText(animal.getRegCity());
  }

  // 資料不符返回true
  private boolean memberCheck(Member member){
    return !StringUtils.hasText(member.getMemberId())
            || !StringUtils.hasText(member.getMemberName())
            || !StringUtils.hasText(member.getPhone())
            || member.getBirth() ==null;
  }
}
