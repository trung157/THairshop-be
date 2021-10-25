package com.thairshop.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public Page<Product> getProductsPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findByStatusOrderByProductIdDesc(0, pageable);
	}

//	@Override
//	public List<Product> getAllProduct(int status) {
//		return productRepository.findByStatusOrderByProductIdDesc(status);
//	}

	@Override
	public Product findByProductId(int productId) {
		return productRepository.findByProductIdAndStatus(productId, 0);
	}

	@Override
	public long getProductSize() {
		
		return productRepository.countByStatus(0);
	}
	
	@Override
	public void saveProduct(ProductDTO productDTO) {
		Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
		Product product = productRepository.save(productConvert.convertEntity(productDTO, category));
		product.setStatus(0);
	}
	@Override
	public void updateProduct(ProductDTO productDTO) {
		Product product = productRepository.findById(productDTO.getProductId()).get();
		
		product.setProductName(productDTO.getProductName());
		product.setQuantity(productDTO.getQuantity());
		product.setPrice(productDTO.getPrice());
		product.setDiscount(productDTO.getDiscount());
		product.setCreatedDate(productDTO.getCreatedDate());
		product.setImage(productDTO.getImage());
		product.setDescription(productDTO.getDescription());
	
		Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
		product.setCategory(category);
		productRepository.save(product);
		
	}
	@Override
	public void deleteProduct(int productId) {
		Product p = productRepository.findByProductIdAndStatus(productId,0);
		p.setStatus(1);
		productRepository.save(p);
	}

	@Override
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	@Override
	public Page<Product> findProductByProductNameAndPageDesc(String productName, int page, int size) {
		Pageable pageable = PageRequest.of(page,size);
		return productRepository.findByStatusAndProductNameContainingOrderByProductIdDesc(0,productName, pageable);
	}
	
	@Override
	public Page<Product> findProductByCategoryId(int categoryId, int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return productRepository.findProductByCategoryId(categoryId, pageable);
	}

	@Override
	public List<Product> findByCategoryId(int categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> getProductTop() {
		return productRepository.findTop8ByStatusOrderByPriceDesc(0);
	}

	@Override
	public Page<Product> findByPriceBetween(int minPrice, int maxPrice, int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return productRepository.findByStatusAndPriceBetween(0, minPrice, maxPrice, pageable);
	}
	
//	@Override
//	public Product getProductDetail(int productId) {
//		return productRepository.getProductDetail(productId);
//	}
	
	
}
