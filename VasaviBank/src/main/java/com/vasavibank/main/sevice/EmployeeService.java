
package com.vasavibank.main.sevice;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.vasavibank.main.dao.EmployeeDao;

public class EmployeeService {

	EmployeeDao employeeDao = new EmployeeDao();

	public void manageEmployee() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		List<Integer> list = employeeDao.getPendingApprovals();
		if(list!=null && list.size() != 0) {
			System.out.println("list of pending Approvals"+list);
			 System.out.println("Please enter the customer Id to update userstatus \n");
				int customerid = scanner.nextInt();
				System.out.println("To approve the customer account enter 1, To Reject the customer account enter 2");
				int option = scanner.nextInt();
					for(int customerId :list) {
						if(customerId==customerid)
						{
							if(option == 1) {
								int number = generateAccountNumber();
								String accountNumber =String.valueOf(number);
								employeeDao.approveCustomer(customerId,accountNumber);
							}else if(option == 2) {
								employeeDao.rejectCustomer(customerId);
						} else {
							System.out.println("Please enter 1 or 2!\n");
						}
				}
					}
				
		 }	else {
			 System.out.println("No pending aprovals found");
		 }
		
	}

	private int generateAccountNumber() {
		Random rand = new Random();
		int resRandom = 0;
			resRandom = rand.nextInt((9999 - 100) + 1) + 10;
			System.out.println(resRandom);
		return resRandom;
	}

}
