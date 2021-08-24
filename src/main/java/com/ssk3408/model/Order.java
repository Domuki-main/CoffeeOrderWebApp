package com.ssk3408.model;

import java.util.List;

public class Order {
	String custID;
	String orderID;
	String orderAddress;
	double totalPrice;
	String paymentMethod;
	String orderDate;
	List<Coffee> coffeeList;
	
	public List<Coffee> getCoffeeList() {
		return coffeeList;
	}
	public void setCoffeeList(List<Coffee> coffeeList) {
		this.coffeeList = coffeeList;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
