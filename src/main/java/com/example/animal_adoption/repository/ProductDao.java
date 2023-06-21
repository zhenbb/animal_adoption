package com.example.animal_adoption.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.animal_adoption.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	// 搜尋關鍵字(正則) 搜尋商品名及分類
//  @Query(value = "SELECT p FROM product AS p "
//          + "WHERE p.product_name REGEXP %:keyword% OR p.category REGEXP %:keyword% ", nativeQuery = true)
//  public List<Product> searchAllByKeywordRegexp(@Param("keyword") String keyword);


	// 搜尋功能(關鍵字*1、分類*2)
	public List<Product> findByProductNameContainingIgnoreCaseAndCategoryContainingAndCategoryContaining
	(String strName,String strCate, String strCate2);

	// 搜尋功能(分類*2)
	public List<Product> findByCategoryContainingAndCategoryContaining
	(String strCate, String strCate2);
	
	// 搜尋功能(關鍵字*1、分類*1)
	public List<Product> findByProductNameContainingIgnoreCaseAndCategoryContaining(String strName, String strCate);

	// 搜尋功能(關鍵字*1)
	public List<Product> findByProductNameContainingIgnoreCase(String strName);

	// 購物車
	@Query(value = "SELECT product_id FROM product", nativeQuery = true)
	public List<Product> byProductId(@Param("input") int input);

	public List<Product> findAllByProductIdIn(List<Integer> productList);
}