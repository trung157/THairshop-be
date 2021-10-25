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

import com.thairshop.demo.dto.ProductDTO;
import com.thairshop.demo.entity.Product;
import com.thairshop.demo.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductService productService;

//	get product va phan trang
	@GetMapping("/products-page")
	public ResponseEntity<Page<Product>> getAll(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(productService.getProductsPage(page, size));
	}

	// list tất cả product hiện có
//	@GetMapping("/list-product")
//	public ResponseEntity<List<Product>> getAll() {
//		return ResponseEntity.ok(productService.getAllProduct(0));
//	}
	@GetMapping("/product-top")
	public ResponseEntity<List<Product>> getProductTop() {
		return ResponseEntity.ok(productService.getProductTop());
	}

	@GetMapping("/list-product-search")
	public ResponseEntity<Page<Product>> getProductSearchAndPage(@RequestParam String productName,
			@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(productService.findProductByProductNameAndPageDesc(productName, page, size));
	}

	@GetMapping("/list-product-by-price")
	public ResponseEntity<Page<Product>> getProductByPriceAndPage(@RequestParam int minPrice,@RequestParam int maxPrice,
			@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(productService.findByPriceBetween(minPrice, maxPrice, page, size));
	}
	
	@GetMapping("/product-by-category")
	public ResponseEntity<Page<Product>> getProductByCategoryId(@RequestParam int categoryId, @RequestParam int page,
			@RequestParam int size) {
		return ResponseEntity.ok(productService.findProductByCategoryId(categoryId, page, size));
	}

	//
	@GetMapping("/product/{productId}")
	public Map<String, Object> getProductById(@PathVariable("productId") int productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product product = productService.findByProductId(productId);
			if (product == null) {
				map.put("status", false);
				return map;
			} else {
				map.put("result", product);
				map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

//	@GetMapping("product/detail/{productId}")
//	public List<Object[]> getProductDetail(@PathVariable("productId") int productId) {
//		return productService.getProductDetail(productId);
//	}

//	Add new product
	@PostMapping("/product")
	public Map<String, Object> post(@Valid @RequestBody ProductDTO product, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (err.hasErrors()) {
				for (ObjectError element : err.getAllErrors()) {
					map.put("error", element.getDefaultMessage());
					return map;
				}
			} else {
				productService.saveProduct(product);
				map.put("status", true);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 500);
		}
		return map;
	}

	// edit product
	@PutMapping("/product/{productId}")
	public Map<String, Object> put(@PathVariable("productId") int productId, @Valid @RequestBody ProductDTO product,
			Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (err.hasErrors()) {
				for (ObjectError element : err.getAllErrors()) {
					map.put("er", element.getDefaultMessage());
					return map;
				}
			} else {
				Product p = productService.findById(product.getProductId()).get();
				if (p == null) {
					map.put("status", false);
					return map;
				} else {
					productService.updateProduct(product);
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
	@DeleteMapping("/product/{productId}")
	public Map<String, Object> delete(@PathVariable("productId") int productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product p = productService.findByProductId(productId);
			if (p == null) {
				map.put("status", false);
				return map;
			} else {
				productService.deleteProduct(productId);
				map.put("status", true);
				return map;
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

// lấy độ dài của product
	@GetMapping("/product-size")
	public long getProductSize() {
		return productService.getProductSize();
	}
}
