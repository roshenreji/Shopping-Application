package com.mindtree.shoppingApplication.DAO;

import java.sql.Connection;

public interface CustomerDAO {

	public void CustomerDetails(Connection con);
	public void updateTotalCost(int id,double cost, Connection con);
	public void getAllItems(Connection con);
}
