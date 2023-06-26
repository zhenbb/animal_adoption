package com.example.animal_adoption.controller;

import com.example.animal_adoption.service.ifs.CartService;
import com.example.animal_adoption.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(value = "addCart")
    public CartResponse addCart(@RequestBody AddCartRequst addCartRequst) {
        return cartService.addProduct(addCartRequst);
    }
    @PostMapping(value = "checkout")
    public CartResponse checkOut(@RequestBody CheckOutRequst checkOutRequst) {
        return cartService.checkOut(checkOutRequst);
    }

    @PostMapping(value = "findMemberCart")
    public CartInfoResponse findMemberCart(@RequestBody FindMemberCartRequest findMemberCartRequest){
        return cartService.findMemberCart(findMemberCartRequest);
    }


    @PostMapping(value = "getCartProduct")
    public CartInfoResponse getCartProduct(@RequestBody GetCartProductRequest getCartProductRequest){
        return cartService.getCartProduct(getCartProductRequest);
    }

    @PostMapping(value = "getOrderProduct")
    public OrderResponse getOrderProduct(@RequestBody GetOrderProductRequest getOrderProductRequest){
        return cartService.getOrderProduct(getOrderProductRequest);
    }
}