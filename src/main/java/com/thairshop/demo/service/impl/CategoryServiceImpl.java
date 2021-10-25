package com.thairshop.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thairshop.demo.convert.CategoryConvert;
import com.thairshop.demo.dto.CategoryDTO;
import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Category;
import com.thairshop.demo.entity.Product;
import com.thairshop.demo.repository.CategoryRepository;
import com.thairshop.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CategoryConvert categoryConvert;
	
	@Override
	public Page<Category> getCategoryPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepository.findByStatusOrderByCategoryIdDesc(0, pageable);
	}
	@Override
	public List<Category> getAllCategory(int status) {
		return categoryRepository.findByStatusOrderByCategoryIdDesc(status);
	}

	@Override
	public Category findByCategoryId(int categoryId) {
		return categoryRepository.findByCategoryIdAndStatus(categoryId, 0);
	}

	@Override
	public long getCategorySize(int status) {
		return categoryRepository.countByStatus(status);
	}

	@Override
	public void saveCategory(CategoryDTO categoryDTO) {
		categoryRepository.save(categoryConvert.convertEntity(categoryDTO));
	}
	
	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(categoryDTO.getCategoryId()).get();
		
		category.setCategoryName(categoryDTO.getCategoryName());
	
		categoryRepository.save(category);
		
	}

	@Override
	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category cate = categoryRepository.findByCategoryIdAndStatus(categoryId,0);
		cate.setStatus(1);
		categoryRepository.save(cate);
		
		
	}

	@Override
	public List<Object[]> findProductByCategoryId(int categoryId) {
		return categoryRepository.findProductByCategoryId(categoryId);
	}
	@Override
	public Page<Category> findCategoryByCategoryNameAndPageDesc(String categoryName, int page, int size) {
		Pageable pageable = PageRequest.of(page,size);
		return categoryRepository.findByStatusAndCategoryNameContainingOrderByCategoryIdDesc(0,categoryName, pageable);
	}
}
