package com.mindtree.shoppingApplication.Service.Impl;

import java.sql.Connection;

import com.mindtree.shoppingApplication.DAO.CustomerDAO;
import com.mindtree.shoppingApplication.DAO.Impl.CustomerDaoImpl;
import com.mindtree.shoppingApplication.Service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	CustomerDAO customer=new CustomerDaoImpl();
	public void addCustomers(Connection con) {
		
		customer.CustomerDetails(con);
	}
	public void updateTotalCost(int id, double cost, Connection con) {
		customer.updateTotalCost(id, cost, con);
		
	}
	public void getAllItems(Connection con) {
		customer.getAllItems(con);
		
	}

	
}
