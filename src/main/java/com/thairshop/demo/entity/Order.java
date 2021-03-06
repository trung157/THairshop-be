package com.thairshop.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Order")
public class Order implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	private Date createdDate;
	
	int totalQuantity;

	int totalPrice;

	private int	status;
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	Account account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	Set<OrderDetail> orderDetails;
	
	public void add(OrderDetail orderDetail) {
		if (orderDetail != null) {
			if (orderDetails == null) {
				orderDetails = new HashSet<>();
			}
			orderDetail.setOrder(this);
			orderDetails.add(orderDetail);
		}
	}
	
}
