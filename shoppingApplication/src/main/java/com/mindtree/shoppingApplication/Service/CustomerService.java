package com.mindtree.shoppingApplication.Service;

import java.sql.Connection;

public interface CustomerService {

	public void addCustomers(Connection con);
	public void updateTotalCost(int id,double cost, Connection con);
	public void getAllItems(Connection con);
}
