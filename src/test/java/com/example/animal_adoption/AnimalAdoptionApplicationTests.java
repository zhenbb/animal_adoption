package com.example.animal_adoption;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal_adoption.service.ifs.AnimalAdoptionService;
import com.example.animal_adoption.vo.ProductAddRequest;
import com.example.animal_adoption.vo.ProductRequest;
import com.example.animal_adoption.vo.ProductResponse;

@SpringBootTest
class AnimalAdoptionApplicationTests {

	private AnimalAdoptionService aniService;

	@Test
	void contextLoads() {
		StringBuffer temp = new StringBuffer("123");
		System.out.println(temp);
		System.out.println(temp.toString());

	}

	// Product 商品用
	@Test
	public void addProductTest() {
		ProductRequest pr = new ProductRequest("花邊項圈(小)","狗,貓",120,10);
		ProductResponse proRes = aniService.addProduct(true, pr);
		System.out.println(proRes.getMessage());
	}
	@Test
	public void updateProductTest() {
		ProductResponse res = aniService.updateProductName(true, 2, "牽繩 3m");
		System.out.println(res.getMessage());
	}



}
