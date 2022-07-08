package com.vasavibank.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vasavibank.main.model.Customer;

public class EmployeeDao {

	public List<Integer> getPendingApprovals() throws SQLException {
		Connection con = VasaviDatabaseConnectivity.getConnection();

		List<Integer> listPendingUser = new ArrayList<Integer>();
		String sql = "select customerId from customer where userstatus = 'PENDING' ";
		PreparedStatement pendingCustomers = con.prepareStatement(sql);
		ResultSet res = pendingCustomers.executeQuery();
		while (res.next()) {
			listPendingUser.add(res.getInt("customerId"));
		}
		return listPendingUser;
	}

	public void approveCustomer(int customerId, String accountNumber) throws SQLException {
		
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "update customer set userstatus = 'ACTIVE'  where customerId =?;";
		PreparedStatement updateUser = con.prepareStatement(query);
		updateUser.setInt(1, customerId);

		int result = updateUser.executeUpdate();
		if (result == 1) {
			System.out.println("customer status updated");
		} else {
			System.out.println("customer status not updated");
		}
		
		String query1 = "update bankaccount set accountnumber=? where customerId =?;";
		PreparedStatement updateAccountNumber = con.prepareStatement(query1);
		updateAccountNumber.setString(1, accountNumber);
		updateAccountNumber.setInt(2, customerId);
		int res = updateAccountNumber.executeUpdate();
			System.out.println("acccount number is updated for the customerId" + customerId);
	}

	public void rejectCustomer(int customerId) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "update customer set userstatus = 'Rejected' where customerId =?;";
		PreparedStatement updateUser = con.prepareStatement(query);
		updateUser.setInt(1, customerId);
		int result = updateUser.executeUpdate();
		if (result == 1) {
			System.out.println("customer status updated");
		} else {
			System.out.println("customer status not updated");
		}
	}

}
