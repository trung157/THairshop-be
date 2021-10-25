package com.thairshop.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thairshop.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query(value = "SELECT c.categoryName, p.productName, p.description, p.quantity,p.image, p.price,p.discount,p.createdDate FROM Product p INNER JOIN Category c ON p.category.categoryId = c.categoryId WHERE c.categoryId = ?1")
	List<Object[]> findProductByCategoryId(int categoryId);

//	hiển thị list category theo thứ tự giảm dần và phân trang
	Page<Category> findByStatusOrderByCategoryIdDesc(int status, Pageable pageable);

//	list all category
	List<Category> findByStatusOrderByCategoryIdDesc(int status);

//  độ dài của category
	long countByStatus(int status);

//  tìm product bằng id
	Category findByCategoryIdAndStatus(int categoryId, int status);
	
	<S extends Category> S save(S entity);
	
	Page<Category> findByStatusAndCategoryNameContainingOrderByCategoryIdDesc(int status,String categoryName, Pageable pageable);
}
