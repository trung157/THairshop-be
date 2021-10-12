package com.thairshop.demo.dto;

import java.util.Date;

	
public class OrderDTO {
	private int OrderId;
	private int AccountId;
	private Date CreatedDate;
	private int	Status;
	
	public OrderDTO() {
		super();		
	}
	public OrderDTO(int orderId, int accountId, Date createdDate, int status) {
		super();
		OrderId = orderId;
		AccountId = accountId;
		CreatedDate = createdDate;
		Status = status;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getAccountId() {
		return AccountId;
	}
	public void setAccountId(int accountId) {
		AccountId = accountId;
	}
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
}
