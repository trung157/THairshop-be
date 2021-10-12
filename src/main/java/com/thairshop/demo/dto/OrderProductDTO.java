package com.thairshop.demo.dto;


public class OrderProductDTO {
	private int OrderProductId;
	private int ProductId;
	private int OrderId;
	private int Amount;
	private int Price;
	
	
	public OrderProductDTO() {
		super();
	}
	public OrderProductDTO(int orderProductId, int productId, int orderId, int amount, int price) {
		super();
		OrderProductId = orderProductId;
		ProductId = productId;
		OrderId = orderId;
		Amount = amount;
		Price = price;
	}
	public int getOrderProductId() {
		return OrderProductId;
	}
	public void setOrderProductId(int orderProductId) {
		OrderProductId = orderProductId;
	}
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	
}
