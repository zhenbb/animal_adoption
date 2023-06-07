package com.example.animal_adoption.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.animal_adoption.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
	
	//搜尋關鍵字(正則)
	@Query(value="SELECT * FROM product AS p"
			+ " WHERE p.name REGEXP %:keyword% OR p.category REGEXP %:keyword%", nativeQuery = true)
	public List<Product> searchAllByKeywordRegexp(@Param("keyword") String str);

	@Query(value = "SELECT product_id FROM product", nativeQuery = true)
	public List<Product> byProductId(@Param("input") int input);

	public List<Product> findAllByProductIdIn(List<Integer> productList);



}
