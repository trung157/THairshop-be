package com.thairshop.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.thairshop.demo.dto.CategoryDTO;
import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Category;

public interface CategoryService {
	Page<Category> getCategoryPage(int page, int size);

	List<Category> getAllCategory(int status);

	Category findByCategoryId(int categoryId);

	long getCategorySize(int status);

	void saveCategory(CategoryDTO categoryDTO);
	
	void updateCategory(CategoryDTO categoryDTO);

	Optional<Category> findById(int id);

	void deleteCategory(int categoryId);
	
	List<Object[]> findProductByCategoryId(int categoryId);
	
	Page<Category> findCategoryByCategoryNameAndPageDesc(String categoryName, int page, int size);
}
