package com.mindtree.shoppingApplication.Entity;

import java.util.List;

public class Customer {

	private int customerId;
	private String customerName;
	private String customerPhoneNumber;
	private String dateOfShopping;
	
	private List<Items> items;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerId, String customerName, String customerPhoneNumber, String dateOfShopping,
			 List<Items> items) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.dateOfShopping = dateOfShopping;
		this.items = items;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getDateOfShopping() {
		return dateOfShopping;
	}
	public void setDateOfShopping(String dateOfShopping) {
		this.dateOfShopping = dateOfShopping;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	
}
