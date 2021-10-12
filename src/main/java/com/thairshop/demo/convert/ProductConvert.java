package com.thairshop.demo.convert;

import org.springframework.stereotype.Component;

import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Product;
import com.thairshop.demo.entity.Category;

@Component
public class ProductConvert {
	public Product convertEntity(ProductDTO productDTO, Category category) {
		Product product = new Product();
		product.setProductName(productDTO.getProductName());
		product.setQuantity(productDTO.getQuantity());
		product.setPrice(productDTO.getPrice());
		product.setDiscount(productDTO.getDiscount());
		product.setCreatedDate(productDTO.getCreatedDate());
		product.setImage(productDTO.getImage());
		product.setDescription(productDTO.getDescription());
		product.setCategory(category);
		return product;
	}
}