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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
  public CartResponse addProduct(AddCartRequst addCartRequst) {
//        取得會員的購物車資料：根據會員的member_id，從資料庫中取得會員的購物車資料。
//        新增商品到購物車：根據商品的資料，建立一個新的Produce物件，並將其加入會員的購物車。
//        更新資料庫：將更新後的購物車資料儲存回資料庫，並更新會員的購物車流水號。

    Member member = addCartRequst.getMember();
    Optional<Member> info = memberDao.findById(member.getMemberId());
    Integer carId = info.get().getCarId();
    Map<Integer, Integer> carMap = addCartRequst.getProducts();
    for (Map.Entry<Integer, Integer> entry : carMap.entrySet()) {
      Integer quantity = entry.getValue();
      if (quantity <= 0) {
        return new CartResponse(RtnCode.INVALID_QUANTITY_ERROR.getMessage());
      }
    }
    List<Integer> cartList = new ArrayList<>(carMap.keySet());
    List<Product> productList = productDao.findAllById(cartList);
//      查詢商品是否存在
    if (productList.isEmpty()) {
      return new CartResponse(RtnCode.NOT_FOUND_PRODUCT_ERROR.getMessage());
    }
//      查詢庫存
    for (Product product : productList) {
      int stock = product.getStock();
      int requestedQuantity = carMap.get(product.getProductId()); // 從 carMap 中取得商品的數量
      if (stock < requestedQuantity) {
        return new CartResponse(RtnCode.ADD_PRODUCT_ERROR.getMessage());
      }
      product.setStock(stock - requestedQuantity);
    }
    productDao.saveAll(productList);
    ObjectMapper mapper = new ObjectMapper();
    String mapStr;
    try {
      mapStr = mapper.writeValueAsString(carMap);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    Car car = new Car();
    car.setCarMap(mapStr);
    //存入商品至購物車
    //更新會員購物車存入
    if (carId == null) {
      carDao.save(car);
      Car cart = carDao.save(car);
      int id = cart.getCarId();
      info.get().setCarId(id);
      memberDao.save(info.get());
      //存入商品至購物車(會員資料中無購物車的)
    } else {
      Optional<Car> oldCart = carDao.findById(carId);
      System.out.println(oldCart);
      String oldcartMapSt = oldCart.get().getCarMap();
      ObjectMapper mapper1 = new ObjectMapper();
      Map<Integer, Integer> oldCartMap = new HashMap<>();
//            將Map字串轉回Map
      try {
        oldCartMap = mapper1.readValue(oldcartMapSt, new TypeReference<Map<Integer, Integer>>() {
        });
        System.out.println(oldCartMap);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      Map<Integer, Integer> newCart = new HashMap<>();
      newCart.putAll(oldCartMap);
      newCart.putAll(carMap);
      String newMapStr;
      try {
        newMapStr = mapper.writeValueAsString(newCart);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
      Car newCar = new Car();
      newCar.setCarMap(newMapStr);
      carDao.save(newCar);
      int newId = newCar.getCarId();
      info.get().setCarId(newId);
      memberDao.save(info.get());
    }
    return new CartResponse(RtnCode.ADD_PRODUCT_SUCCESS.getMessage());
  }

  @Override
  public CartInfoResponse findMemberCart(FindMemberCartRequest findMemberCartRequest) {
    Optional<Member> member = memberDao.findById(findMemberCartRequest.getMember().getMemberId());
    Integer carId = member.get().getCarId();
    Optional<Car> cart = carDao.findById(carId);
    String cartMap = cart.get().getCarMap();
    ObjectMapper mapper = new ObjectMapper();
    Map<Integer, Integer> shoppingCartMap = new HashMap<>();
    //將Map字串轉回Map
    try {
      shoppingCartMap = mapper.readValue(cartMap, new TypeReference<Map<Integer, Integer>>() {
      });
      System.out.println(shoppingCartMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    List<Integer> productIdList = new ArrayList<>(shoppingCartMap.keySet());
    List<Product> orderList = productDao.findAllByProductIdIn(productIdList);
    String shoppingCartMapJson = new Gson().toJson(shoppingCartMap);


    return new CartInfoResponse(orderList, shoppingCartMapJson);
  }


  @Override
  public CartResponse checkOut(CheckOutRequst checkOutRequst) {

    Optional<Member> member = memberDao.findById(checkOutRequst.getMember().getMemberId());
    Optional<Car> memberCart = carDao.findById(member.get().getCarId());
    String memberCartMapSt = memberCart.get().getCarMap();
    ObjectMapper mapper = new ObjectMapper();
    Map<Integer, Integer> memberCartMap = new HashMap<>();
    //將Map字串轉回Map`
    try {
      memberCartMap = mapper.readValue(memberCartMapSt, new TypeReference<Map<Integer, Integer>>() {
      });
//            System.out.println(memberCartMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    Integer[] productId = checkOutRequst.getProductId();
    Integer[] sales = checkOutRequst.getSales();
    Map<Integer, Integer> orderMap = new LinkedHashMap<>();
    for (int i = 0; i < productId.length; i++) {
      orderMap.put(productId[i], sales[i]);
    }
    List<Integer> productIdList = new ArrayList<>(orderMap.keySet());
    List<Product> orderList = productDao.findAllByProductIdIn(productIdList);
    String newMemberCartMapSt;
    try {
      newMemberCartMapSt = mapper.writeValueAsString(memberCartMap);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    if (memberCartMap.isEmpty()) {
      member.get().setCarId(null);
      memberDao.save(member.get());
    }
    else {
      Car car = new Car();
      car.setCarMap(newMemberCartMapSt);
      carDao.save(car);
      member.get().setCarId(carDao.save(car).getCarId());
      int carId = car.getCarId();
      member.get().setCarId(carId);
      memberDao.save(member.get());
    }
    Order order = new Order();
    order.setCheckoutMap(newMemberCartMapSt);
    System.out.println(newMemberCartMapSt);
    orderDao.save(order);
    Order order2 = orderDao.findByCheckoutMap(newMemberCartMapSt);
    if (member.get().getCheckoutId() == null) {
      String checkoutId = order2.getCheckoutId() + "," + " ";
      member.get().setCheckoutId(checkoutId);
    }
    else {
      String newCheckoutId = member.get().getCheckoutId() + order2.getCheckoutId() + "," + " ";
      member.get().setCheckoutId(newCheckoutId);
    }
    int totalPrice = 0;
    for (Product product : orderList) {
      for (Map.Entry<Integer, Integer> item : orderMap.entrySet()) {
        if (item.getValue() < 0) {
          return new CartResponse(RtnCode.NOT_FOUND_PRODUCT_ERROR.getMessage());
        }
        Integer key = item.getKey();
        if (key.equals(product.getProductId())) {
          int quantity = item.getValue();
          if (quantity > product.getStock()) {
            return new CartResponse(RtnCode.OUT_OF_STOCK_ERROR.getMessage());
          }
          totalPrice += product.getPrice() * quantity;
          product.setStock(product.getStock() - quantity);
          memberCartMap.remove(item.getKey());
        }
      }
    }
    member.get().setCarId(null);
    memberDao.save(member.get());
    return new CartResponse("總金額為：" + totalPrice + RtnCode.ADD_ORDER_SUCCESS.getMessage());
  }

  @Override
  public CartInfoResponse getCartProduct(GetCartProductRequest getCartProductRequest) {

    Optional<Member> member = memberDao.findById(getCartProductRequest.getMember().getMemberId());
    Integer carId = member.get().getCarId();
    System.out.println(carId);
    //從會員資料中找到購物車ID
    //查詢購物車ID
    Optional<Car> cart = carDao.findById(carId);
    //找到購物車Map字串
    String cartMap = cart.get().getCarMap();
    ObjectMapper mapper = new ObjectMapper();
    Map<Integer, Integer> shoppingCartMap = new HashMap<>();
    //將Map字串轉回Map
    try {
      shoppingCartMap = mapper.readValue(cartMap, new TypeReference<Map<Integer, Integer>>() {
      });
      System.out.println(shoppingCartMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    //將字串的Key值加入List並從資料庫中找到商品
    List<Integer> productIdList = new ArrayList<>(shoppingCartMap.keySet());
    List<Product> orderList = productDao.findAllByProductIdIn(productIdList);

    return new CartInfoResponse(orderList, "購物車物品");
  }

  @Override
  public CartResponse modifyProductQuantity(DeleteProductRequest deleteProductRequest) {

    Optional<Member> member = memberDao.findById(deleteProductRequest.getMember().getMemberId());
    Integer carId = member.get().getCarId();
    System.out.println(carId);
    //從會員資料中找到購物車ID
    //查詢購物車ID
    Optional<Car> cart = carDao.findById(carId);
    //找到購物車Map字串
    String cartMap = cart.get().getCarMap();
    ObjectMapper mapper = new ObjectMapper();
    Map<Integer, Integer> shoppingCartMap = new HashMap<>();
    //將Map字串轉回Map
    try {
      shoppingCartMap = mapper.readValue(cartMap, new TypeReference<Map<Integer, Integer>>() {
      });
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    List<Integer> productIdList = new ArrayList<>(shoppingCartMap.keySet());
    List<Product> orderList = productDao.findAllByProductIdIn(productIdList);
    List<Product> saveList = new ArrayList<>();
    for (Product product : orderList) {
      for (Map.Entry<Integer, Integer> item : shoppingCartMap.entrySet()) {
        if (item.getKey().equals(deleteProductRequest.getProductId())) {
          item.setValue(deleteProductRequest.getQuantity());
        }
        Integer key = item.getKey();
        if (key.equals(product.getProductId())) {
          int sales = deleteProductRequest.getQuantity();
          if (sales > product.getStock()) {
            return new CartResponse(RtnCode.OUT_OF_STOCK_ERROR.getMessage());
          }
          product.setStock(product.getStock() + key - sales);
          saveList.add(product);
        }
      }
    }
    return new CartResponse(RtnCode.MODIFY_THE_QUANTITY.getMessage());
  }

  @Override
  public OrderResponse getOrderProduct(GetOrderProductRequest getOrderProductRequest) {

    Optional<Member> member = memberDao.findById(getOrderProductRequest.getMember().getMemberId());
    String order = member.get().getCheckoutId();
    String[] orderIdArr = order.split(",\\s*"); // 使用逗號和空白分割訂單編號

    List<OrderItem> orderList = new ArrayList<>();

    for (String orderId : orderIdArr) {
      Optional<Order> orderItem = orderDao.findById(Integer.parseInt(orderId));
      if (orderItem.isPresent()) {
        String cartMap = orderItem.get().getCheckoutMap();
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Integer> orderCartMap = new HashMap<>();
        try {
          orderCartMap = mapper.readValue(cartMap, new TypeReference<Map<Integer, Integer>>() {
          });
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
        List<Integer> productIdList = new ArrayList<>(orderCartMap.keySet());
        List<Product> products = productDao.findAllByProductIdIn(productIdList);

        // 計算總價格
        int totalPrice = 0;
        for (Product product : products) {
          int quantity = orderCartMap.get(product.getProductId());
          totalPrice += product.getPrice() * quantity;
        }

        // 建立 OrderItem 物件，包含訂單商品資訊、數量和總價格
        OrderItem orderItem1 = new OrderItem(products, orderCartMap, totalPrice);
        orderList.add(orderItem1);
      }
    }
    if (orderList.isEmpty()) {
      return new OrderResponse(RtnCode.NOT_FOUND.getMessage());
    }
    return new OrderResponse(orderList);
  }

}