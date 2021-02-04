package com.mindtree.shoppingApplication.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.mindtree.shoppingApplication.Exceptions.IdAbsentException;
import com.mindtree.shoppingApplication.Exceptions.NameAbsentException;
import com.mindtree.shoppingApplication.Exceptions.QuantityException;
import com.mindtree.shoppingApplication.Service.ShoppingService;
import com.mindtree.shoppingApplication.Service.Impl.ShoppingServiceImpl;

public class CustomerShopping {

	Scanner sc = new Scanner(System.in);

	public void checkIdAbsence(int id, Connection con) throws IdAbsentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from Shop Where Id=" + id;
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkNameAbsence(String name, Connection con) throws NameAbsentException {
		String count = "";
		boolean valid = false;
		try {

			String query = "Select name from Shop Where name=" + name;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getString(1);
				if (count.equals(name)) {
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkQuantity(int quantity, int id, Connection con) throws QuantityException {
		int count = 0;
		int idOfItem=0;
		boolean valid = false;
		try {

			String query = "Select id,quantity from Shop Where id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				idOfItem = resultSet.getInt(1);
				count = resultSet.getInt(2);
				if (idOfItem==id) {
					if (quantity < count) {
						valid = true;

					}
					break;

				}

			}

			if (valid == false) {
				throw new IdAbsentException("Quantity Exceeded");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}
	
	public int currentQuantity(int quantity, int id, Connection con) throws QuantityException {
		int count = 0;
		int idOfItem=0;
		int currentQuantity=0;
		boolean valid = false;
		try {

			String query = "Select id,quantity from Shop Where id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				idOfItem = resultSet.getInt(1);
				count = resultSet.getInt(2);
				if (idOfItem==id) {
					currentQuantity=count;
					break;

				}

			}

			

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return currentQuantity;
	}

	public void displayMessages() {
		System.out.println();
		System.out.println("Welcome to Shopping");
		System.out.println("====================");
		System.out.println("These are the choices");
		System.out.println("1. View Items In the Shop");
		System.out.println("2. Get Item");
		System.out.println("3. Exit");
		System.out.println("Enter your choice: ");
	}
	
	public double priceOfTheItem(int id,int quantity,Connection con) {
		double count = 0;
		int idOfItem=0;
		double price=0;
		try {

			String query = "Select id,pricePerItem from Shop Where id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				idOfItem = resultSet.getInt(1);
				count = resultSet.getDouble(2);
				if (idOfItem==id) {
					price=count*quantity;

				}

			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return price;
	}

	public Map<Integer,Double> shoppingCustomer(Connection con,Map<Integer,Double> map) {
		boolean isValid = true;
		ShoppingService items = new ShoppingServiceImpl();
		do {
			displayMessages();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				items.getAllItems(con);
				break;
			case 2:
				System.out.println("Enter Item ID of the product you want to purchase");
				int id=sc.nextInt();
				try {
					checkIdAbsence(id, con);
					
					System.out.println("Enter the quantity which you need?");
					int quantity = sc.nextInt();
					try {
						checkQuantity(quantity,id,con);
						int newQuantity=currentQuantity(quantity,id, con);
						items.updateItemQuantity(newQuantity-quantity, id, con);
						double price=priceOfTheItem(id,quantity,con);
						map.put(id, price);
						
					} catch (QuantityException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				} catch (IdAbsentException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				isValid=false;
				break;
				default:System.out.println("Invalid Option, please try again");

			}
		} while (isValid);
		return map;
	}
	
	

}
