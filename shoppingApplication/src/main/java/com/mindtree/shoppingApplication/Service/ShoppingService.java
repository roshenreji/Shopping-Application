package com.mindtree.shoppingApplication.Service;

import java.sql.Connection;
import java.util.List;

import com.mindtree.shoppingApplication.Entity.Items;

public interface ShoppingService {

	public void addItems(Connection con);
	public void updateItemQuantity(int quantity,int id,Connection con);
	public void deleteItem(int id,Connection con);
	public void getAllItems(Connection con);
}
