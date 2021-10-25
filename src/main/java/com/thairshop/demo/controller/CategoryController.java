package com.thairshop.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thairshop.demo.dto.CategoryDTO;
import com.thairshop.demo.entity.Category;
import com.thairshop.demo.entity.Product;
import com.thairshop.demo.service.CategoryService;
import com.thairshop.demo.service.ProductService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;

//	get category và phân trang
	@GetMapping("/categorys-page")
	public ResponseEntity<Page<Category>> getAll(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(categoryService.getCategoryPage(page, size));
	}

	// list tất cả category hiện có
	@GetMapping("/list-category")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.getAllCategory(0));
	}
	
	@GetMapping("/list-category-search")
	public ResponseEntity<Page<Category>> getCategorySearchAndPage(@RequestParam String categoryName,
			@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(categoryService.findCategoryByCategoryNameAndPageDesc(categoryName, page, size));
	}

	@GetMapping("/category/{categoryId}")
	public Map<String, Object> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Category category = categoryService.findByCategoryId(categoryId);
			if(category == null) {
				 map.put("status", false);
	             return map;
			}
			else {
				map.put("result", category);
                map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;	
	}

//	Add new product
	@PostMapping("/category")
	public Map<String, Object> post(@Valid @RequestBody CategoryDTO category, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for (ObjectError element : err.getAllErrors()) {
					map.put("er", element.getDefaultMessage());
					return map;
				}
			}else {
				List<Category> list = categoryService.getAllCategory(0);
				for (Category cate : list) {
					if (cate.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
						map.put("status", false);
						return map;
					}else {
						categoryService.saveCategory(category);
						map.put("status", true);
						return map;
					}
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

	// edit category
	@PutMapping("/category/{categoryId}")
	public Map<String, Object> put(@PathVariable("categoryId") int categoryId,@Valid @RequestBody CategoryDTO category,Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for (ObjectError element : err.getAllErrors()) {
					map.put("er", element.getDefaultMessage());
					return map;
				}
			}
			else {
				Category cate = categoryService.findByCategoryId(categoryId);
				if(cate == null) {
					map.put("status", false);
					return map;
				}
				else {
					categoryService.updateCategory(category);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

//	xóa product
	@DeleteMapping("/category/{categoryId}")
	public Map<String, Object> delete(@PathVariable("categoryId") int categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Category c = categoryService.findByCategoryId(categoryId);
			List<Product> list = productService.findByCategoryId(categoryId);
			if(c == null) {
				map.put("status", false);
				return map;
			}
			else {
				if(list.size() > 0) {
					map.put("status", 501);
					return map;
				}
				else {
					categoryService.deleteCategory(categoryId);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

// lấy độ dài của category
	@GetMapping("/category-size")
	public long getCategorySize() {
		return categoryService.getCategorySize(0);
	}
	
	@GetMapping("/category/p/{categoryId}")
	public List<Object[]> findProductByCategory(@PathVariable("categoryId") int categoryId){
		return categoryService.findProductByCategoryId(categoryId);
	}
}
