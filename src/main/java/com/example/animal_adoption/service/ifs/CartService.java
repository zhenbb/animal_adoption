package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.AddCartRequst;
import com.example.animal_adoption.vo.CartResponse;
import com.example.animal_adoption.vo.CheckOutRequst;


public interface CartService {

    public CartResponse addProduct(AddCartRequst requst);

    public CartResponse checkOut(CheckOutRequst checkOutRequst);
}
