package com.thairshop.demo.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

import org.springframework.format.annotation.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

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
	@NotNull(message = "CategoryId is not null!")
	private int categoryId;
	@NotNull(message = "CreateDate is not null!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	@NotNull
	private int status;

	public ProductDTO(String productName, int quantity, int price, int discount, String image,Date createdDate, String description, 
			int categoryId, int status) {
		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.image = image;
		this.price = price;
		this.discount = discount;
		this.categoryId = categoryId;
		this.createdDate = createdDate;
		this.status = status;
	}

}
