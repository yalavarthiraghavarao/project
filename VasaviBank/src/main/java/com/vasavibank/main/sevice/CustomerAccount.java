package com.vasavibank.main.sevice;

import java.sql.SQLException;
import java.util.Scanner;

import com.vasavibank.main.dao.CustomerDao;
import com.vasavibank.main.model.Customer;

public class CustomerAccount {
	
	 CustomerDao customerDao=new CustomerDao();

	public void createAccount() throws SQLException
	{
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("enter your firstname");
		String firstName=scanner.next();
		System.out.println("enter your lastname");
		String lastName=scanner.next();
		
		System.out.println("enter your emailId");
		String email=scanner.next();
		
		System.out.println("enter your password");
		String password=scanner.next();
		
		System.out.println("enterinitial deposit amount");
		Double depositMoney=scanner.nextDouble();
		
		 Customer customer=new  Customer();
		 customer.setFirstName(firstName);
		 customer.setLastName(lastName);
		 customer.setEmailId(email);
		 customer.setPassword(password);
		
	
		 
		 customerDao.createAccount(customer,depositMoney);
		 
	
	}

	public int validateLogin(String emailId, String password) throws SQLException {

		return customerDao.validateLogin(emailId,password);
	}

	public void depositMoney(int customerId, double amount) throws SQLException {
		// TODO Auto-generated method stub
		customerDao.depositeMoney(customerId,amount);
	}

	public void withdrawMoney(int customerId, double amount) throws SQLException {
		// TODO Auto-generated method stub
		customerDao.withdrawMoney(customerId,amount);
	}

	public void viewAccountBalance(int customerId) throws SQLException {
		// TODO Auto-generated method stub
		customerDao.viewAccountBalance(customerId);
	}

	public void moneyTransfer(int customerId,String accountNumber, double amount)  throws SQLException {
		// TODO Auto-generated method stub
		customerDao.moneyTransfer(customerId,accountNumber,amount);
	}

}
