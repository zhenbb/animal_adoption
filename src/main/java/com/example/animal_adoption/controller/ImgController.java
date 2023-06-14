package com.example.animal_adoption.controller;

import com.example.animal_adoption.util.Base64ToImg2;
import com.example.animal_adoption.vo.ImgRequest;
import com.example.animal_adoption.vo.ImgResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ImgController {

  @PostMapping(value = "countImg")
  public ImgResponse countImg(@RequestBody ImgRequest imgRequest){
    return Base64ToImg2.countImg(imgRequest.getSort(),imgRequest.getId());
  }
}
