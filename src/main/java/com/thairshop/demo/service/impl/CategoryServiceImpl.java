package com.thairshop.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thairshop.demo.convert.CategoryConvert;
import com.thairshop.demo.dto.CategoryDTO;
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
	public List<Category> getCategorysPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepository.findByStatusOrderByCategoryIdDesc(0, pageable).getContent();
	}

	@Override
	public List<Category> getAllCategory(int status) {
		return categoryRepository.findByStatusOrderByCategoryIdDesc(status);
	}

	@Override
	public Category findByCategoryId(int categoryId, int status) {
		return categoryRepository.findByCategoryIdAndStatus(categoryId, status);
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
	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteCategory(int status, int categoryId) {
		Category cate = categoryRepository.findByCategoryIdAndStatus(status,categoryId);
		cate.setStatus(1);
		categoryRepository.save(cate);
		
	}

	@Override
	public List<Object[]> findProductByCategoryId(int categoryId) {
		return categoryRepository.findProductByCategoryId(categoryId);
	}
}
