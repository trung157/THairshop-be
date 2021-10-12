package com.thairshop.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.*;

import lombok.*;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Product")
public class Product implements Serializable {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@NotNull
	private int productId;
	
	@NotBlank(message = "ProductName is not null!")
	private String productName;
	
	@NotBlank(message = "Description is not null!")
	private String description;
	
	@NotNull(message = "Quantity is not null!")
	@PositiveOrZero(message = "Quantity cannot be less than 0!")
	private int quantity;
	
	@NotBlank(message = "Image is not null!")
	private String image;
	
	@NotNull(message = "Price is not null!")
	@PositiveOrZero(message = "Price cannot be less than 0!")
	private int price;
	
	@NotNull(message = "Discount is not null!")
	@PositiveOrZero(message = "Discount cannot be less than 0!")
	private int discount;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@NotNull(message = "Category is not null!")
	Category category;
	
	@NotNull(message = "CreateDate is not null!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	
	@NotNull
	@ColumnDefault("0")
	private int status;
	
}
