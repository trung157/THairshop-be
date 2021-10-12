package com.thairshop.demo.service;

import java.util.List;
import java.util.Optional;

import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Product;

public interface ProductService {
	List<Product> getProductsPage(int page, int size);

	List<Product> getAllProduct(int status);

	Product findByProductId(int productId, int status);

	long getProductSize(int status);

	void saveProduct(ProductDTO productDTO);

	Optional<Product> findById(int id);

	void deleteProduct(int status, int productId);
}
