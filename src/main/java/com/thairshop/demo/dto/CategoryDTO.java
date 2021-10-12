package com.thairshop.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	private int categoryId;
	private String categoryName;
	private int status;
	public CategoryDTO(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
