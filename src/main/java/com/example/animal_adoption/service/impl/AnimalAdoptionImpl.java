package com.example.animal_adoption.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.animal_adoption.util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.repository.AnimalDao;
import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.EligibilityReviewRequest;
import com.example.animal_adoption.vo.FavoriteRequest;

@Service
public class AnimalAdoptionImpl implements AnimalAdoptionService {

  @Autowired
  HttpSession session;

  @Autowired
  AnimalDao animalDao;

  @Autowired
  MemberDao memberDao;

  @Autowired
  ProductDao productDao;

  @Override
  // 認養申請
  // 會員需先登入
  public AnimalAdoptionResponse adoption(AdoptionRequest adoptionRequest) {

    // 取得客戶端Session
    //HttpSession clientSession = adoptionRequest.getHttpSession();
    //// 驗證客戶端Id與伺服器端Id，判斷是否有登入
    //String serviceSession = (String) session.getAttribute(clientSession.getId());
    //if (!StringUtils.hasText(serviceSession)) {
    //    return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
    //}

    // 取出欲領寵物資訊
    Animal animal = adoptionRequest.getAnimal();
    // 取出欲領養人資訊
    Optional<Member> temp1 = memberDao.findById( adoptionRequest.getMemberId());
    if (!temp1.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }
    Member member = temp1.get();

    // 判斷資料是否為空
    if (animalCheck(animal) || memberCheck(member)) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領寵物是否存在
    Optional<Animal> temp2 = animalDao.findById(animal.getAnimalId());
    if (!temp2.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    Animal theAnimal = temp2.get();
    // 判寵物是否尚未被領養
    if (theAnimal.getAdoptDate() != null) {
      return new AnimalAdoptionResponse(RtnCode.HAS_BEEN_ADOPTED_ERROR.getMessage());
    }

    Mail mail = new Mail();
    mail.sendMail(animal.toString() + member.toString());
    return new AnimalAdoptionResponse(theAnimal, member, RtnCode.SUBMIT_SUCCESS.getMessage());
  }

  @Override
  // 認養資格審查
  // 會員需先登入
  public AnimalAdoptionResponse eligibilityReview(EligibilityReviewRequest reviewRequest) {

    //// 取得伺服器Session
    //HttpSession clientSession = reviewRequest.getHttpSession();
    //// 驗證客戶端Id與伺服器端Id，判斷是否有登入
    //String serviceSession = (String) session.getAttribute(clientSession.getId());
    //if (!StringUtils.hasText(serviceSession)) {
    //    return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
    //}

    // 取出欲領寵物資訊
    Animal animal = reviewRequest.getAnimal();
    // 取出欲領養人資訊
    Member member = reviewRequest.getMember();

    // 判斷資料是否為空
    if (animalCheck(animal) || memberCheck(member)) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領寵物是否存在
    Optional<Animal> temp1 = animalDao.findById(animal.getAnimalId());
    if (!temp1.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    Animal theAnimal = temp1.get();
    // 判寵物是否尚未被領養
    if (theAnimal.getAdoptDate() != null) {
      return new AnimalAdoptionResponse(RtnCode.HAS_BEEN_ADOPTED_ERROR.getMessage());
    }

    // 判斷領養人是否存在
    if (!memberDao.existsById(member.getMemberId())) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 更新認養日期與認養人
    theAnimal.setAdoptDate(LocalDate.now());
    theAnimal.setMemberId(member.getMemberId());
    animalDao.save(theAnimal);

    return new AnimalAdoptionResponse(theAnimal, member, RtnCode.REVIEW_SUCCESS.getMessage());
  }

  @Override
  // 增加收藏
  // 會員需先登入
  public AnimalAdoptionResponse addFavorite(FavoriteRequest favoriteRequest) {

    //// 取得客戶端Session
    //HttpSession clientSession = favoriteRequest.getHttpSession();
    //// 驗證客戶端Id與伺服器端Id，判斷是否有登入
    //String serviceSession = (String) session.getAttribute(clientSession.getId());
    //if (!StringUtils.hasText(serviceSession)) {
    //    return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
    //}

    // 取出會員資訊
    Optional<Member> temp1 = memberDao.findById(favoriteRequest.getMemberId());
    if (!temp1.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }
    Member member = temp1.get();

    // 取出會員收寵物資訊
    StringBuffer favAnimal = new StringBuffer();
    if (StringUtils.hasText(member.getFav())){
      favAnimal.append(member.getFav());
    }

    // 判斷資料是否為空
    if (memberCheck(member)) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領養人是否存在
    if (!memberDao.existsById(member.getMemberId())) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 取出動物資訊
    Optional<Animal> temp2 = animalDao.findById(favoriteRequest.getAnimalId());
    if (!temp2.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }
    Animal animal = temp2.get();


    // 判斷資料是否為空
    if (animalCheck(animal)) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領寵物是否存在
    if (!animalDao.existsById(animal.getAnimalId())) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 保存收寵物資訊
    favAnimal.append(animal.getAnimalId()).append(",");


    member.setFav(favAnimal.toString());
    memberDao.save(member);
    return new AnimalAdoptionResponse(RtnCode.ADD_FAVORITE_SUCCESS.getMessage());
  }

  @Override
  // 刪除收藏
  // 會員需先登入
  public AnimalAdoptionResponse deleteFavorite(FavoriteRequest favoriteRequest) {

    //// 取得客戶端Session
    //HttpSession clientSession = favoriteRequest.getHttpSession();
    //// 驗證客戶端Id與伺服器端Id，判斷是否有登入
    //String serviceSession = (String) session.getAttribute(clientSession.getId());
    //if (!StringUtils.hasText(serviceSession)) {
    //    return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
    //}

    // 取出會員資訊
    Optional<Member> temp1 = memberDao.findById(favoriteRequest.getMemberId());
    if (!temp1.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }
    Member member = temp1.get();
    // 取出會員收寵物資訊
    StringBuffer favAnimal = new StringBuffer();
    if (StringUtils.hasText(member.getFav())){
      favAnimal.append(member.getFav());
    }

    // 判斷資料是否為空
    if (memberCheck(member)) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領養人是否存在
    if (!memberDao.existsById(member.getMemberId())) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 取出動物資訊
    Optional<Animal> temp2 = animalDao.findById(favoriteRequest.getAnimalId());
    if (!temp2.isPresent()) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }
    Animal animal = temp2.get();

    // 判斷資料是否為空
    if (animalCheck(animal)) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 判斷領寵物是否存在
    if (!animalDao.existsById(animal.getAnimalId())) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 取得收藏寵物的Id在收藏字串的哪個位置
    String id = Integer.toString(animal.getAnimalId());
    int index = favAnimal.indexOf(id);
    // -1代表收藏寵物的Id不存在收藏字串裡
    if (index < 0) {
      return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
    }

    // 刪除收藏。+1是因為包含逗號
    favAnimal.delete(index, index + id.length() + 1);

    member.setFav(favAnimal.toString());
    memberDao.save(member);
    return new AnimalAdoptionResponse(RtnCode.ADD_FAVORITE_SUCCESS.getMessage());
  }

  // 資料不符返回true
  private boolean animalCheck(Animal animal) {
    return animal == null || animal.getAnimalId() < 1 || !StringUtils.hasText(animal.getAnimalName())
            || !StringUtils.hasText(animal.getType()) || animal.getRegDate() == null
            || !StringUtils.hasText(animal.getRegCity());
  }

  // 資料不符返回true
  private boolean memberCheck(Member member) {
    return member == null || !StringUtils.hasText(member.getMemberId())
            || !StringUtils.hasText(member.getMemberName()) || !StringUtils.hasText(member.getPhone())
            || member.getBirth() == null;
  }

}