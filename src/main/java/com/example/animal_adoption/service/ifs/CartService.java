package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.*;


public interface CartService {

    public CartResponse addProduct(AddCartRequst addCartRequst);

    public CartInfoResponse findMemberCart(FindMemberCartRequest findMemberCartRequest);

    public CartResponse checkOut(CheckOutRequst checkOutRequst);

    public CartInfoResponse getCartProduct(GetCartProductRequest getCartProductRequest);

    public CartResponse modifyProductQuantity(DeleteProductRequest deleteProductRequest);

    public CartResponse getOrderProduct(GetOrderProductRequest getOrderProductRequest);
}