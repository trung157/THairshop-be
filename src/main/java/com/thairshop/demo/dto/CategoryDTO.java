package com.thairshop.demo.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	@NotNull
	private int categoryId;
	@NotBlank(message = "ProductName is not null!")
	private String categoryName;
	@NotNull
	private int status;
	public CategoryDTO(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
