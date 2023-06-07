package com.example.animal_adoption.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Animal;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.AnimalDao;
import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.AdoptionRequest;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.EligibilityReviewRequest;
import com.example.animal_adoption.vo.FavoriteRequest;
import com.example.animal_adoption.vo.ProductRequest;
import com.example.animal_adoption.vo.ProductResponse;

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
        HttpSession clientSession = adoptionRequest.getHttpSession();
        // 驗證客戶端Id與伺服器端Id，判斷是否有登入
        String serviceSession = (String) session.getAttribute(clientSession.getId());
        if (!StringUtils.hasText(serviceSession)) {
            return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
        }

        // 取出欲領寵物資訊
        Animal animal = adoptionRequest.getAnimal();
        // 取出欲領養人資訊
        Member member = adoptionRequest.getMember();

        // 判斷資料是否為空
        if (animalCheck(animal) || memberCheck(member)) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        // 判斷領寵物是否存在
        Optional<Animal> temp = animalDao.findById(animal.getAnimalId());
        if (!temp.isPresent()) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        // 判斷領養人是否存在
        if (!memberDao.existsById(member.getMemberId())) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        Animal theAnimal = temp.get();
        // 判寵物是否尚未被領養
        if (theAnimal.getAdoptDate() != null) {
            return new AnimalAdoptionResponse(RtnCode.HAS_BEEN_ADOPTED_ERROR.getMessage());
        }

        return new AnimalAdoptionResponse(theAnimal, member, RtnCode.SUBMIT_SUCCESS.getMessage());
    }

    @Override
    // 增加我的最愛
    // 會員需先登入
    public AnimalAdoptionResponse eligibilityReview(EligibilityReviewRequest reviewRequest) {

        // 取得伺服器Session
        HttpSession clientSession = reviewRequest.getHttpSession();
        // 驗證客戶端Id與伺服器端Id，判斷是否有登入
        String serviceSession = (String) session.getAttribute(clientSession.getId());
        if (!StringUtils.hasText(serviceSession)) {
            return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
        }

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
    // 批次增加收藏，僅保存寵物資訊正確收藏，不正確寵物不保存
    // 會員需先登入
    public AnimalAdoptionResponse addFavorite(FavoriteRequest favoriteRequest) {

        // 取得客戶端Session
        HttpSession clientSession = favoriteRequest.getHttpSession();
        // 驗證客戶端Id與伺服器端Id，判斷是否有登入
        String serviceSession = (String) session.getAttribute(clientSession.getId());
        if (!StringUtils.hasText(serviceSession)) {
            return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
        }

        // 取出會員資訊
        Member member = favoriteRequest.getMember();
        // 取出會員收寵物資訊
        StringBuffer favAnimal = new StringBuffer(member.getFav());
        // 用於保存失敗訊息
        List<String> messageList = new ArrayList<>();
        // 用於保存收藏失敗寵物訊息
        List<Animal> failAnimal = new ArrayList<>();
        // 用於計算第幾筆資料
        int count = 1;

        // 判斷資料是否為空
        if (memberCheck(member)) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        // 判斷領養人是否存在
        if (!memberDao.existsById(member.getMemberId())) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        for (Animal animal : favoriteRequest.getAnimal()) {

            // 判斷資料是否為空
            if (animalCheck(animal)) {
                messageList.add("第" + count + "筆資料: " + RtnCode.INCORRECT_INFO_ERROR.getMessage());
                failAnimal.add(animal);
                continue;
            }

            // 判斷領寵物是否存在
            if (animalDao.existsById(animal.getAnimalId())) {
                messageList.add("第" + count + "筆資料: " + RtnCode.INCORRECT_INFO_ERROR.getMessage());
                failAnimal.add(animal);
                continue;
            }

            // 保存收寵物資訊
            favAnimal.append(animal.getAnimalId()).append(",");
            messageList.add("第" + count + "筆資料: " + RtnCode.ADD_FAVORITE_SUCCESS.getMessage());
        }

        member.setFav(favAnimal.toString());
        memberDao.save(member);
        return new AnimalAdoptionResponse(failAnimal, messageList);
    }

    @Override
    // 批次刪除，僅刪除寵物資訊正確收藏，不正確寵物不執行
    // 會員需先登入
    public AnimalAdoptionResponse deleteFavorite(FavoriteRequest favoriteRequest) {

        // 取得客戶端Session
        HttpSession clientSession = favoriteRequest.getHttpSession();
        // 驗證客戶端Id與伺服器端Id，判斷是否有登入
        String serviceSession = (String) session.getAttribute(clientSession.getId());
        if (!StringUtils.hasText(serviceSession)) {
            return new AnimalAdoptionResponse(RtnCode.NOT_LOGGED_IN.getMessage());
        }

        // 取出會員資訊
        Member member = favoriteRequest.getMember();
        // 取出會員收寵物資訊
        StringBuffer favAnimal = new StringBuffer(member.getFav());
        // 用於保存失敗訊息
        List<String> messageList = new ArrayList<>();
        // 用於保存刪除失敗寵物訊息
        List<Animal> failAnimal = new ArrayList<>();
        // 用於計算第幾筆資料
        int count = 1;

        // 判斷資料是否為空
        if (memberCheck(member)) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        // 判斷領養人是否存在
        if (!memberDao.existsById(member.getMemberId())) {
            return new AnimalAdoptionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
        }

        for (Animal animal : favoriteRequest.getAnimal()) {

            // 判斷資料是否為空
            if (animalCheck(animal)) {
                messageList.add("第" + count + "筆資料: " + RtnCode.INCORRECT_INFO_ERROR.getMessage());
                failAnimal.add(animal);
                continue;
            }

            // 判斷領寵物是否存在
            if (animalDao.existsById(animal.getAnimalId())) {
                messageList.add("第" + count + "筆資料: " + RtnCode.INCORRECT_INFO_ERROR.getMessage());
                failAnimal.add(animal);
                continue;
            }

            // 取得收藏寵物的Id在收藏字串的哪個位置
            String id = Integer.toString(animal.getAnimalId());
            int index = favAnimal.indexOf(id);
            // -1代表收藏寵物的Id不存在收藏字串裡
            if (index < 0) {
                messageList.add("第" + count + "筆資料: " + RtnCode.INCORRECT_INFO_ERROR.getMessage());
            }

            // 刪除收藏。+1是因為包含逗號
            favAnimal.delete(index, index + id.length() + 1);
            messageList.add("第" + count + "筆資料: " + RtnCode.ADD_FAVORITE_SUCCESS.getMessage());
        }

        member.setFav(favAnimal.toString());
        memberDao.save(member);
        return new AnimalAdoptionResponse(failAnimal, messageList);

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


    // 新增商品 nana
    @Override
    public ProductResponse addProduct(boolean isAdministrator, ProductRequest productRequest) {
        // 防呆 null/空白
        if (productRequest == null || !StringUtils.hasText(productRequest.getProductName())
                || !StringUtils.hasText(productRequest.getCategory())) {
            return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
        }

        // 非管理員
        if (isAdministrator == false) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
        }

        // 數字錯誤
        if (productRequest.getPrice() <= 0 || productRequest.getStock() < 0) {
            return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
        }

        // 確認資料庫裡有沒有
//		List<Product> resultList = productDao.findByName();
//		if(!CollectionUtils.isEmpty(resultList)) {
//			System.out.println("有重複名字的商品，是否要重複新增？");
//		}
        Product product = new Product(0, productRequest.getProductName(), productRequest.getCategory(),
                productRequest.getPrice(), productRequest.getStock());
        productDao.save(product);

        return new ProductResponse(product, RtnCode.PRODUCT_ADD_SUCCESS.getMessage());
    }

    // 1. 更新商品庫存
    @Override
    public ProductResponse updateProductStock(boolean isAdministrator, int productId, int stock) {

        // 非管理員
        if (isAdministrator == false) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
        }

        // 防呆
        if (productId <= 0 || stock < 0) {
            return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
        }

        Product result = productDao.findById(productId).get();
        result.setStock(stock);
        productDao.save(result);
        return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
    }

    // 2. 更新商品名稱
    @Override
    public ProductResponse updateProductName(boolean isAdministrator, int productId, String productName) {

        // 非管理員
        if (isAdministrator == false) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
        }

        // 防呆
        if (productId <= 0) {
            return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(productName)) {
            return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
        }

        Product result = productDao.findById(productId).get();
        result.setProductName(productName);
        productDao.save(result);
        return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
    }

    // 3. 更新商品價格
    @Override
    public ProductResponse updateProductPrice(boolean isAdministrator, int productId, int price) {

        // 非管理員
        if (isAdministrator == false) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
        }

        // 防呆
        if (productId <= 0 || price < 0) {
            return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
        }

        Product result = productDao.findById(productId).get();
        result.setPrice(price);
        productDao.save(result);
        return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
    }

    // 4. 更新商品分類
    @Override
    public ProductResponse updateProductCategory(boolean isAdministrator, int productId, String category) {
        // 非管理員
        if (isAdministrator == false) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_ADMINISTRATOR.getMessage());
        }
        // 防呆
        if (productId < 0) {
            return new ProductResponse(RtnCode.PRODUCT_DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(category)) {
            return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
        }

        // 搜尋是否有這本書
        Product result = productDao.findById(productId).get();

        if (result == null) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
        }

        // 檢查分類是否沒修改
        // 步驟1/3--抓出字串+去頭去尾+分割+加入List
        String opCate = result.getCategory().substring(1, result.getCategory().length() - 1);
        String bookCate = category.substring(1, category.length() - 1);
        List<String> opCateList = Arrays.asList(opCate.split(", "));
        List<String> bookCateList = Arrays.asList(bookCate.split(", "));
        // 步驟2/3--先比較List長度，只要不同就直接上傳
        if (opCateList.size() != bookCateList.size()) {
            result.setCategory(category);
            productDao.save(result);
            return new ProductResponse(RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
        }
        // 步驟3/3--同樣長度的[]-->比較內容是否完全一樣
        int count = 0;
        for (String bookArr : bookCateList) {
            if (opCateList.contains(bookArr)) {
                count++;
                continue;
            }
        }
        // 假如新的分類完全等於原始的分類 => 錯誤訊息"無項目變更"
        if (count == opCateList.size()) {
            return new ProductResponse(RtnCode.PRODUCT_NO_CHANGE.getMessage());
        }

        //完成後上傳
        result.setCategory(category);
        productDao.save(result);
        return new ProductResponse(result, RtnCode.PRODUCT_UPDATE_SUCCESS.getMessage());
    }


    //搜尋功能(可多關鍵字、可用空格區分)@Query+regexp
    @Override
    public ProductResponse searchKeyword(String keyword) {

        //防呆
        if (!StringUtils.hasText(keyword)) {
            return new ProductResponse(RtnCode.PRODUCT_CANNOT_EMPTY.getMessage());
        }

        //正則拆解
        List<String> keywordList = Arrays.asList(keyword.trim().split(" "));
        String regKeyword = String.join("|", keywordList);

        //搜尋
        List<Product> result = productDao.searchAllByKeywordRegexp(regKeyword);
        if (result.size() <= 0) {
            return new ProductResponse(RtnCode.PRODUCT_NOT_FOUND.getMessage());
        }

        return new ProductResponse(result, RtnCode.PRODUCT_SEARCH_SUCCESS.getMessage());
    }

}
