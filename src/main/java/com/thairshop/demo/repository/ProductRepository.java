package com.thairshop.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thairshop.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
//	hiển thị list product theo thứ tự giảm dần và phân trang
	Page<Product> findByStatusOrderByProductIdDesc(int status,Pageable pageable);
//	list all product
	List<Product> findByStatusOrderByProductIdDesc(int status);
// tìm product bằng id
	Product findByProductIdAndStatus(int productId,int status);
// độ dài của product
	long countByStatus(int status);
}