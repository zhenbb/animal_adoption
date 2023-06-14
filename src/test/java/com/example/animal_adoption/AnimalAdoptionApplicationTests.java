package com.example.animal_adoption;

<<<<<<< HEAD
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.example.animal_adoption.util.Base64ToImg2;
import org.junit.jupiter.api.Test;
>>>>>>> Base64ToImg_test
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.ProductDao;

@SpringBootTest
class AnimalAdoptionApplicationTests {

<<<<<<< HEAD
	@Autowired
	ProductDao productDao;
	
	@Test
	public void findAllProduct() {
		List<Product> result = productDao.findAll();
		System.out.println(result.size());
	}
=======


>>>>>>> Base64ToImg_test
}
