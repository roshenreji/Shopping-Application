package com.mindtree.shoppingApplication.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.mindtree.shoppingApplication.Exceptions.IdAbsentException;
import com.mindtree.shoppingApplication.Service.CustomerService;
import com.mindtree.shoppingApplication.Service.ShoppingService;
import com.mindtree.shoppingApplication.Service.Impl.CustomerServiceImpl;
import com.mindtree.shoppingApplication.Service.Impl.ShoppingServiceImpl;
import com.mindtree.shoppingApplication.Utility.JDBCConnection;

public class ShoppingApplication {
	
	 Scanner sc=new Scanner(System.in);
	public void displayMessages() {
		System.out.println();
		System.out.println("These are the choices: ");
		System.out.println("1. Sign Up Customer");
		System.out.println("2. Sign In Customer");
		System.out.println("3. Generate Shopping Report");
		System.out.println("4. Add Items to the Application");
		System.out.println("5. Update Item's Quantity");
		System.out.println("6. Delete the Item");
		System.out.println("7. Get all items and print in sorted order by price and quantity");
		System.out.println("8. Exit");
		System.out.println("Enter your choice: ");
		
	}
	public void checkIdAbsence(int id, Connection con) throws IdAbsentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from Customer Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new IdAbsentException("Entry Not Found");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}
	
	public void generateCustomerReport(Map<Integer,Double>map,Connection con,CustomerService customers,int id) {
		CustomerService obj=new CustomerServiceImpl();
		double totalCost=0;
		
		Set<Integer> keys=map.keySet();
		for(int idOfCustomer:keys) {
			totalCost=totalCost+map.get(idOfCustomer);
		}
		
		customers.updateTotalCost(id, totalCost, con);
		
	}

	public static void main(String[] args) {
		
		boolean isValid=true;
		int firstTime=1;
		ShoppingApplication obj=new ShoppingApplication();
		JDBCConnection connect = new JDBCConnection();
		CustomerService customers=new CustomerServiceImpl();
		Connection con = connect.getConnection();
		CustomerShopping shopping=new CustomerShopping();
		ShoppingService items=new ShoppingServiceImpl();
		Map<Integer,Double> map=new HashMap<Integer,Double>();
		
		do {
			obj.displayMessages();
			int choice=obj.sc.nextInt();
			switch(choice) {
			case 1:
				customers.addCustomers(con);
				firstTime++;
				break;
			case 2:
				if(firstTime!=1) {
					System.out.println("Enter Customer Id");
					int id=obj.sc.nextInt();
					try {
						obj.checkIdAbsence(id,con);
						 map=shopping.shoppingCustomer(con,map);
						obj. generateCustomerReport(map,con,customers,id);
					} catch (IdAbsentException e) {
						System.out.println(e.getMessage());
					}
				}
				
				break;
			case 3:
				if(firstTime!=1) {
					customers.getAllItems(con);
				}
				
				break;
			case 4:
				items.addItems(con);
				break;
			case 5:
				System.out.println("Enter the id of the item whose quantity you needs to change: ");
				int id=obj.sc.nextInt();
				System.out.println("Enter the new quantity: ");
				int quantity=obj.sc.nextInt();
				items.updateItemQuantity(quantity, id, con);
				break;
			case 6:
				System.out.println("Enter the id of the item which you want to delete: ");
				int idOfItem=obj.sc.nextInt();
				items.deleteItem(idOfItem,con);
				break;
			case 7:
				items.getAllItems(con);
				break;
			case 8:
				isValid=false;
				
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
				default:
					System.out.println("Invalid Option, Please try Again");
				
			}
			
		}
		while(isValid);
		

	}

}
