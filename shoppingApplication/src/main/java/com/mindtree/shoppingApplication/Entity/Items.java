package com.mindtree.shoppingApplication.Entity;

public class Items {

	private int id;
	private String name;
	private double pricePerItem;
	private int quantity;
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Items(int id, String name, double pricePerItem, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.pricePerItem = pricePerItem;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
