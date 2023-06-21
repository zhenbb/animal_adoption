package com.example.animal_adoption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Product;
import com.example.animal_adoption.repository.ProductDao;
import com.example.animal_adoption.vo.ProductResponse;

@SpringBootTest
class AnimalAdoptionApplicationTests {

	@Autowired
	ProductDao productDao;
	
	@Test
	public void findAllProduct() {
		List<Product> result = productDao.findAll();
		System.out.println(result.size());
	}
	
	@Test
	public void listTest() {
		String cate = "A, B,C,,E,";
		String obCate = "B,C,D";
	
		// 資料庫裡的分類
		List<String> opCateList = Arrays.asList(obCate.split(",")); 

		// input分類
		String newCate = cate.replaceAll(" ", ""); //清掉空白
		List<String> bookCateList = new ArrayList<>();

		String[] bookCateArray = newCate.split(",");
	    for (String category : bookCateArray) {
	        if (!category.isEmpty()) { // 過濾空值
	            bookCateList.add(category);
	        }
	    }
		System.out.println(opCateList);
		System.out.println(newCate);
		System.out.println(bookCateList);
		
				
	}
}
