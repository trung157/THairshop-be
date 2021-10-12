package com.thairshop.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thairshop.demo.convert.ProductConvert;
import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Category;
import com.thairshop.demo.entity.Product;
import com.thairshop.demo.repository.CategoryRepository;
import com.thairshop.demo.repository.ProductRepository;
import com.thairshop.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductConvert productConvert;
	@Override
	public List<Product> getProductsPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findByStatusOrderByProductIdDesc(0, pageable).getContent();
	}

	@Override
	public List<Product> getAllProduct(int status) {
		return productRepository.findByStatusOrderByProductIdDesc(status);
	}

	@Override
	public Product findByProductId(int productId, int status) {
		return productRepository.findByProductIdAndStatus(productId, status);
	}

	@Override
	public long getProductSize(int status) {
		
		return productRepository.countByStatus(status);
	}
	
	@Override
	public void saveProduct(ProductDTO productDTO) {
		Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
		Product product = productRepository.save(productConvert.convertEntity(productDTO, category));
		product.setStatus(0);
	}

	@Override
	public void deleteProduct(int status, int productId) {
		Product p = productRepository.findByProductIdAndStatus(status,productId);
		p.setStatus(1);
		productRepository.save(p);
	}

	@Override
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}
	
}
