package com.thairshop.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Product;

public interface ProductService {
	Page<Product> getProductsPage(int page, int size);

//	List<Product> getAllProduct(int status);

	Product findByProductId(int productId);

	long getProductSize();

	void saveProduct(ProductDTO productDTO);

	void updateProduct(ProductDTO productDTO);
	
	Optional<Product> findById(int id);

	void deleteProduct(int productId);
	
	Page<Product> findProductByProductNameAndPageDesc(String productName, int page, int size);

	Page<Product> findProductByCategoryId(int categoryId, int page, int size);
	
	List<Product> findByCategoryId(int categoryId);
	
	List<Product> getProductTop();
	
	Page<Product> findByPriceBetween(int minPrice, int maxPrice, int page,int size);
}
