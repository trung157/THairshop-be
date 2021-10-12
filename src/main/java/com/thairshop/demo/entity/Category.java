package com.thairshop.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Category")
public class Category implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@NotBlank(message = "CategoryName is not null!")
	private String categoryName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;	
	
	@NotNull
	@ColumnDefault("0")
	private int status;
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
