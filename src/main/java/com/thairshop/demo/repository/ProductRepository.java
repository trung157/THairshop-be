package com.thairshop.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thairshop.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
//	hiển thị list product theo thứ tự giảm dần và phân trang
	Page<Product> findByStatusOrderByProductIdDesc(int status,Pageable pageable);
//	list all product
//	List<Product> findByStatusOrderByProductIdDesc(int status);
// tìm product bằng id
	Product findByProductIdAndStatus(int productId,int status);
// độ dài của product
	long countByStatus(int status);
	
	Page<Product> findByStatusAndProductNameContainingOrderByProductIdDesc(int status,String productName, Pageable pageable);
	
//	@Query(value = "SELECT c.categoryName, p.productName, p.description, p.quantity,p.image, p.price,p.discount,p.createdDate FROM Product p INNER JOIN Category c ON p.category.categoryId = c.categoryId WHERE p.status = 0 AND c.categoryId = ?1")
	@Query("SELECT p FROM Product p INNER JOIN Category c ON p.category.categoryId = c.categoryId "
			+ "WHERE p.status=0 AND c.categoryId=?1")
	Page<Product> findProductByCategoryId(int categoryId, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.status = 0 and p.category.categoryId =?1")
	List<Product> findByCategoryId(int categoryId);
	
	List<Product> findTop8ByStatusOrderByPriceDesc(int status);
	
	Page<Product> findByStatusAndPriceBetween(int status,int minPrice, int maxPrice, Pageable pageable);
	
}