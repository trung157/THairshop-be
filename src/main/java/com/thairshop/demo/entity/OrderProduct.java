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
@Table(name="Order_Product")
public class OrderProduct implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private int orderProductId;

	private int amount;

	private int price;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	Product product;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	Order order;
	
}
