package com.example.animal_adoption.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.animal_adoption.entity.Product;
 
@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
	
	//搜尋關鍵字(正則) 搜尋商品名及分類
	@Query(value="SELECT * FROM product AS p"
			+ " WHERE p.product_name REGEXP %:keyword% AND p.category REGEXP %:keyword%", nativeQuery = true)
	public List<Product> searchAllByKeywordRegexp(@Param("keyword") String str);

	//購物車
	@Query(value = "SELECT product_id FROM product", nativeQuery = true)
	public List<Product> byProductId(@Param("input") int input);

	public List<Product> findAllByProductIdIn(List<Integer> productList);


	//前端會需要的功能：
	//展示所有商品 (商品頁)
	public List<Product> findAllOrderByProductIdDesc();
	
	//展示最新12個商品(首頁)
	public List<Product> findTop12OrderByProductIdDesc();
	
	//展示銷量最好前四 (啊..突然想到我們資料庫沒有這個東東)
//	public List<Product> findTop4OrderBySold();

}