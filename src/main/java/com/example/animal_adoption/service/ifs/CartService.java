package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.CartRequst;
import com.example.animal_adoption.vo.CartResponse;

public interface CartService {

    public CartResponse addProduct(CartRequst requst);

}
