package com.example.animal_adoption;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.ProductDao;

@SpringBootTest
class AnimalAdoptionApplicationTests {

	@Autowired
	ProductDao productDao;
	
	@Test
	public void findAllProduct() {
		List<Product> result = productDao.findAll();
		System.out.println(result.size());
	}
}
