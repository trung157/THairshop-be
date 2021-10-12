package com.thairshop.demo.convert;

import org.springframework.stereotype.Component;

import com.thairshop.demo.dto.CategoryDTO;
import com.thairshop.demo.entity.Category;

@Component
public class CategoryConvert {
	public Category convertEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setCategoryName(categoryDTO.getCategoryName());
		return category;
	}
}