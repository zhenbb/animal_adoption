package com.example.animal_adoption.service.impl;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.repository.CarDao;
import com.example.animal_adoption.service.ifs.CartService;
import com.example.animal_adoption.vo.CartRequst;
import com.example.animal_adoption.vo.CartResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartImpl implements CartService {

    @Autowired
    private CarDao carDao;

    @Override
    public CartResponse addProduct(CartRequst requst) {
        Map<String, Integer> cart = requst.getCarMap();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String mapstr = mapper.writeValueAsString(cart);
            Map<String, Integer> resMap = mapper.readValue(mapstr, Map.class);
            for(Map.Entry<String, Integer> item : resMap.entrySet()){
            }
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }



        return new CartResponse();
    }
}
