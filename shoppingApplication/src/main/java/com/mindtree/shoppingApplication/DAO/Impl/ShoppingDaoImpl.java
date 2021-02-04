package com.mindtree.shoppingApplication.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mindtree.shoppingApplication.DAO.ShoppingDAO;
import com.mindtree.shoppingApplication.Exceptions.IdAbsentException;
import com.mindtree.shoppingApplication.Exceptions.IdPresentException;
import com.mindtree.shoppingApplication.Utility.JDBCConnection;



public class ShoppingDaoImpl implements ShoppingDAO {

	
	Scanner sc = new Scanner(System.in);

	public void checkIdPresence(int id, Connection con) throws IdPresentException {
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

			if (valid == true) {
				throw new IdPresentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

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

	public void addItems(Connection con) {
		String query = "Insert into Shop values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("Enter Item  id: ");
			int id = sc.nextInt();
			System.out.println("Enter Item Name: ");
			String itemName = sc.next();
			System.out.println("Enter Item Quantity");
			int quantity = sc.nextInt();
			System.out.println("Enter Price per Item");
			double pricePerItem = sc.nextDouble();

			ps.setInt(1, id);
			ps.setString(2, itemName);
			ps.setInt(3, quantity);
			ps.setDouble(4, pricePerItem);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void updateItemQuantity(int quantity, int id,Connection con) {
		try {
			checkIdAbsence(id, con);
			try {

				String query = "Update Shop Set Quantity=" + quantity + " Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} catch (IdAbsentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void deleteItem(int id,Connection con) {
		try {
			checkIdAbsence(id, con);
			try {

				String query = "DELETE from Shop Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		} catch (IdAbsentException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

	}

	public void getAllItems(Connection con) {
		try {

			int count = 1;

			String query = "Select id,name,quantity,pricePerItem from Shop Order By pricePerItem,quantity";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				System.out.println();
				System.out.println(count + "\t Item Id: " + rs.getInt(1));
				System.out.println("\t Item Name: " + rs.getString(2));
				System.out.println("\t Item Quantity: " + rs.getInt(3));
				System.out.println("\t Item Price: " + rs.getDouble(4));

				count++;
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}
