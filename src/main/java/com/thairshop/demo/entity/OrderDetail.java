package com.thairshop.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Order_Detail")
public class OrderDetail implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private int orderDetailId;

	private int quantity;

	private int price;
	
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	Product product;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	Order order;
	
}
