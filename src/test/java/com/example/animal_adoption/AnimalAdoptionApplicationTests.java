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

}
