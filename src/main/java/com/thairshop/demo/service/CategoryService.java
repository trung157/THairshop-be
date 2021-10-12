package com.thairshop.demo.service;

import java.util.List;
import java.util.Optional;

import com.thairshop.demo.dto.CategoryDTO;
import com.thairshop.demo.entity.Category;

public interface CategoryService {
	List<Category> getCategorysPage(int page, int size);

	List<Category> getAllCategory(int status);

	Category findByCategoryId(int categoryId, int status);

	long getCategorySize(int status);

	void saveCategory(CategoryDTO categoryDTO);

	Optional<Category> findById(int id);

	void deleteCategory(int status, int productId);
	
	List<Object[]> findProductByCategoryId(int categoryId);
}
