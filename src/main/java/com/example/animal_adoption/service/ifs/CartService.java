package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.AddCartRequst;
import com.example.animal_adoption.vo.CartResponse;
import com.example.animal_adoption.vo.CheckOutRequst;
import com.example.animal_adoption.vo.DeleteCartProductRequst;


public interface CartService {

    public CartResponse addProduct(AddCartRequst addCartRequst);

    public CartResponse deleteProduct(DeleteCartProductRequst deleteCartProductRequst);

    public CartResponse checkOut(CheckOutRequst checkOutRequst);

}
