package com.example.animal_adoption.controller;

import com.example.animal_adoption.service.ifs.CartService;
import com.example.animal_adoption.vo.AddCartRequst;
import com.example.animal_adoption.vo.CartResponse;
import com.example.animal_adoption.vo.CheckOutRequst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(value = "add_cart")
    public CartResponse addCart(@RequestBody AddCartRequst addCartRequst) {
        return cartService.addProduct(addCartRequst);
    }
    @PostMapping(value = "checkout")
    public CartResponse checkOut(@RequestBody CheckOutRequst checkOutRequst) {
        return cartService.checkOut(checkOutRequst);
    }


}
