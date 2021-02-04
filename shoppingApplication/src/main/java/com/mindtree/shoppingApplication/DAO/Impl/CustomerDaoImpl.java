package com.mindtree.shoppingApplication.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mindtree.shoppingApplication.DAO.CustomerDAO;
import com.mindtree.shoppingApplication.Exceptions.IdAbsentException;

public class CustomerDaoImpl implements CustomerDAO {

	Scanner sc = new Scanner(System.in);

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void CustomerDetails(Connection con) {
		String query = "Insert into Customer values(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("Enter Customer  id: ");
			int id = sc.nextInt();
			System.out.println("Enter Customer Name: ");
			String name = sc.next();
			System.out.println("Enter Customer Phone Number");
			String phoneNumber = sc.next();

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, phoneNumber);
			ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setDouble(5, 0);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void updateTotalCost(int id,double cost, Connection con) {
		try {
			checkIdAbsence(id,con);
			try {
				String query = "Update Customer Set totalCost=" + cost + " Where Id=" + id;
				PreparedStatement ps = con.prepareStatement(query);

				ps.executeUpdate();

				if (ps != null) {
					ps.close();
				}

			}
			
		 catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		} catch (IdAbsentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getAllItems(Connection con) {
		try {

			int count = 1;

			String query = "Select id,name,phoneNumber,time,totalCost from Customer";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				System.out.println();
				System.out.println(count + "\t Customer Id: " + rs.getInt(1));
				System.out.println("\t Customer Name: " + rs.getString(2));
				System.out.println("\t Customer PhoneNumber: " + rs.getString(3));
				System.out.println("\t Customer Timevof Purchase: " + rs.getTimestamp(4));
				System.out.println("\t Customer TotalCost: " + rs.getDouble(5));

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
