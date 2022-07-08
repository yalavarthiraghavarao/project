package com.vasavibank.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vasavibank.main.model.Customer;

public class CustomerDao {
	public void createAccount(Customer customer, Double depositMoney) throws SQLException {
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "INSERT INTO customer(FirstName,LastName,emailId,password,userstatus)  values(?,?,?,?,'PENDING')";
		PreparedStatement st = con.prepareStatement(query);

		st.setString(1, customer.getFirstName());
		st.setString(2, customer.getLastName());
		st.setString(3, customer.getEmailId());
		st.setString(4, customer.getPassword());
		int r = st.executeUpdate();

		String query2 = "select Max(customerId) from customer";
		

		PreparedStatement st1 = con.prepareStatement(query2);
		ResultSet resultSet = st1.executeQuery();

		int newCustomerId;
		if (resultSet.next()) {
			newCustomerId = resultSet.getInt("MAX(customerId)");
		} else {
			throw new SQLException();
		}

		String query1 = "INSERT INTO bankaccount(customerId,initialdeposite,accountbalance)  values(?,?,?)";

		PreparedStatement applyNewBanking = con.prepareStatement(query1);
		applyNewBanking.setInt(1, newCustomerId);
		applyNewBanking.setDouble(2,depositMoney);
		applyNewBanking.setDouble(3,depositMoney);

		int res = applyNewBanking.executeUpdate();

		if (res == 1) {
			System.out.println("successfully added");
		} else {
			System.out.println("not successfully addeed");
		}

	}

	public int validateLogin(String emailId, String password) throws SQLException {
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "select customerId from customer where emailId=? and password=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, emailId);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("customerId");
		}
		return 0;
	}

	public void depositeMoney(int customerId, double amount) throws SQLException {
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "select accountbalance from bankaccount where customerId=?";
		PreparedStatement querystmt = con.prepareStatement(query);
		querystmt.setInt(1, customerId);
		double accountbalance = 0;
		ResultSet rs = querystmt.executeQuery();
		if (rs.next()) {
			accountbalance= rs.getDouble("accountbalance");
		}
		amount = accountbalance+amount;
		String query1 = "update  bankaccount set accountbalance=? where customerId=?";
		PreparedStatement deposite = con.prepareStatement(query1);
		deposite.setDouble(1, amount);
		deposite.setInt(2, customerId);
		deposite.executeUpdate();
		System.out.println("Deposited succesfully");
	}

	public void withdrawMoney(int customerId, double withdrawamount) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "select accountbalance from bankaccount where customerId=?";
		PreparedStatement querystmt = con.prepareStatement(query);
		querystmt.setInt(1, customerId);
		double amount = 0;
		ResultSet rs = querystmt.executeQuery();
		if (rs.next()) {
			amount= rs.getDouble("accountbalance");
		}
		double amountcheck =amount- withdrawamount;
		if(amountcheck>0) {
			System.out.println("you have withdraw:"+withdrawamount);
			String updatequery = "update bankaccount set accountbalance=? where customerId=?";
			PreparedStatement stmt = con.prepareStatement(updatequery);
			stmt.setDouble(1, amountcheck);
			stmt.setInt(2,customerId);
			stmt.executeUpdate();
		}else {
			System.out.println("you have insufficient balance");
		}
	}

	public void viewAccountBalance(int customerId) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "select accountbalance from bankaccount where customerId=?";
		PreparedStatement querystmt = con.prepareStatement(query);
		querystmt.setInt(1, customerId);
		double amount = 0;
		ResultSet rs = querystmt.executeQuery();
		if (rs.next()) {
			amount= rs.getDouble("accountbalance");
		}
		System.out.println("Total Amount in your accoun:t"+amount);
	}

	public void moneyTransfer(int customerId, String accountNumber, double transferAmount) throws SQLException {
		Connection con = VasaviDatabaseConnectivity.getConnection();
		String query = "select accountbalance from bankaccount where customerId=?";
		PreparedStatement querystmt = con.prepareStatement(query);
		querystmt.setInt(1, customerId);
		double amount = 0;
		ResultSet rs = querystmt.executeQuery();
		if (rs.next()) {
			amount= rs.getDouble("accountbalance");
		}
		double remaingAmonut =amount- transferAmount;
		if(remaingAmonut>0) {
			System.out.println("updating remaing balance");
			String updatequery = "update bankaccount set accountbalance=? where customerId=?";
			PreparedStatement stmt = con.prepareStatement(updatequery);
			stmt.setDouble(1, remaingAmonut);
			stmt.setInt(2,customerId);
			stmt.executeUpdate();
			System.out.println("Money has been debited from your account");
			String query1 = "select accountbalance from bankaccount where accountnumber=?";
			PreparedStatement query1stmt = con.prepareStatement(query1);
			query1stmt.setString(1, accountNumber);
			double resamount = 0;
			ResultSet rs1 = query1stmt.executeQuery();
			if (rs1.next()) {
				resamount= rs1.getDouble("accountbalance");
			}
			transferAmount = resamount+transferAmount;
			System.out.println("transferring money");
			String insertamount = "update bankaccount set accountbalance=? where accountnumber=?";
			PreparedStatement insertstmt = con.prepareStatement(insertamount);
			insertstmt.setString(2, accountNumber);
			insertstmt.setDouble(1, transferAmount);
			insertstmt.executeUpdate();
			System.out.println("Money Transferred successfully");
		}else {
			System.out.println("you dont have insuffience balance to transfer");
		}
	}

}
