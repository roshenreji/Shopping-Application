package com.mindtree.shoppingApplication.Service.Impl;

import java.sql.Connection;
import java.util.List;

import com.mindtree.shoppingApplication.DAO.ShoppingDAO;
import com.mindtree.shoppingApplication.DAO.Impl.ShoppingDaoImpl;
import com.mindtree.shoppingApplication.Entity.Items;
import com.mindtree.shoppingApplication.Service.ShoppingService;

public class ShoppingServiceImpl implements ShoppingService {

	ShoppingDAO dao=new ShoppingDaoImpl();
	public void addItems(Connection con) {
		dao.addItems(con);
		
	}

	public void updateItemQuantity(int quantity, int id,Connection con) {
		dao.updateItemQuantity(quantity, id,con);
		
	}

	public void deleteItem(int id,Connection con) {
		dao.deleteItem(id,con);
		
	}

	public void getAllItems(Connection con) {
		dao.getAllItems(con);
		
	}

}
