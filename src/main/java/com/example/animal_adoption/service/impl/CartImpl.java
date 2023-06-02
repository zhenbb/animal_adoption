package com.example.animal_adoption.service.impl;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Car;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.entity.Order;
import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.CarDao;
import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.repository.OrderDao;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.service.ifs.CartService;
import com.example.animal_adoption.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartImpl implements CartService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public CartResponse addProduct(AddCartRequst requst) {
//        取得會員的購物車資料：根據會員的member_id，從資料庫中取得會員的購物車資料。
//        新增商品到購物車：根據商品的資料，建立一個新的Produce物件，並將其加入會員的購物車。
//        更新資料庫：將更新後的購物車資料儲存回資料庫，並更新會員的購物車流水號。

        Member member = requst.getMember();
        Integer carId = member.getCarId();

        List<Product> cartList = new ArrayList<>(productDao.byProductId(requst.getProductId()));
        //查詢商品是否存在
        if (cartList.isEmpty()) {
            return new CartResponse(RtnCode.NOT_FOUND_PRODUCT_ERROR.getMessage());
        }
        //查詢庫存
        for (Product item : cartList) {
            int stock = item.getStock();
            if (stock < requst.getSales()) {
                return new CartResponse(RtnCode.ADD_PRODUCT_ERROR.getMessage());
            }
        }

        //將商品放入Map轉換成字串存入資料庫
        Map<Integer, Integer> shoppingCart = new HashMap<>(requst.getProductId(), requst.getSales());
        ObjectMapper mapper = new ObjectMapper();
        String mapStr;
        try {
            mapStr = mapper.writeValueAsString(shoppingCart);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Car car = new Car();
        car.setCarMap(mapStr);
        //存入商品至購物車
        //更新會員購物車存入
        if (carId == null) {
            carDao.save(car);
            Car cart = carDao.byCarMap(mapStr);
            int id = cart.getCarId();
            member.setCarId(id);
            memberDao.save(member);
            //存入商品至購物車(會員資料中無購物車的)
        } else {
            Map<Integer, Integer> newCart = new HashMap<>();
            newCart.putAll(shoppingCart);
            String newMapStr;
            try {
                newMapStr = mapper.writeValueAsString(newCart);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Car newCar = new Car();
            newCar.setCarMap(newMapStr);
            carDao.save(newCar);
        }
        return new CartResponse(RtnCode.ADD_PRODUCT_SUCCESS.getMessage());
    }

    @Override
    public CartResponse checkOut(CheckOutRequst checkOutRequst) {


        //從會員資料中找到購物車ID
        int carid = checkOutRequst.getCarId();
        //查詢購物車ID
        Car cart = carDao.byCarId(carid);
        //找到購物車Map字串
        String cartMap = cart.getCarMap();
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Integer> shoppingCartMap = new HashMap<>();
        //將Map字串轉回Map
        try {
            shoppingCartMap = mapper.readValue(cartMap, Map.class);
//            System.out.println(shoppingCart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //將字串的Key值加入List並從資料庫中找到商品
        List<Integer> productIdList = new ArrayList<>(shoppingCartMap.keySet());
        List<Product> orderList = productDao.findAllByProductId(productIdList);
        List<Product> saveList = new ArrayList<>();
        int totalPrice = 0;
        for (Product product : orderList) {
            for (Map.Entry<Integer, Integer> item : shoppingCartMap.entrySet()) {
                if (item.getValue() < 0) {
                    return new CartResponse(RtnCode.NOT_FOUND_PRODUCT_ERROR.getMessage());
                }
                Integer key = item.getKey();
                if (key.equals(product.getProductId())) {
                    int sales = item.getValue();
                    if (sales > product.getStock()) {
                        return new CartResponse(RtnCode.OUT_OF_STOCK_ERROR.getMessage());
                    }
                    totalPrice += product.getPrice() * sales;
                    product.setStock(product.getStock() - sales);
                    saveList.add(product);
                }
            }
        }
        productDao.saveAll(saveList);
        Order newOrder = new Order();
        newOrder.setOrderMap(cartMap);
        orderDao.save(newOrder);
        return new CartResponse(RtnCode.ADD_ORDER_SUCCESS.getMessage());
    }
}

