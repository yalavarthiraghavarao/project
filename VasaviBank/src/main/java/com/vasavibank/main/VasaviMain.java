package com.vasavibank.main;


import java.sql.SQLException;
import java.util.Scanner;



import com.vasavibank.main.sevice.CustomerAccount;
import com.vasavibank.main.sevice.EmployeeService;

public class VasaviMain {
	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------");
		System.out.println("******welcome to vasavi bank*****");
		System.out.println("-------------------");
		System.out.println("choose 1 If you are employee ,choose 2 if you are customer");
		int yourchoice = scanner.nextInt();

		CustomerAccount customerAccount = new CustomerAccount();

		EmployeeService employeeService = new EmployeeService();

		switch (yourchoice) {
		case 1:
			
			System.out.println("loggged in as employee");
			System.out.println("enter 1 to register as a customer ,enter 2 to see list of pending approvals");
			int employeeChoice = scanner.nextInt();
			if (employeeChoice == 1) {
				System.out.println("welcome to vasavi bank ,,you are new customer,,create your account");
				customerAccount.createAccount();
			} else if (employeeChoice == 2) {
				employeeService.manageEmployee();
			} else {
				System.out.println("you have entered wrong input");
			}
			break;

		case 2:
			
			System.out.println("logged in  as customer");
			System.out.println("if you are existing customer enter 1 ,new customer enter 2");

			int choice = scanner.nextInt();
			if (choice == 1) {
				System.out.println("enter emailId");
				String emailId = scanner.next();
				System.out.println("ente password");
				String password = scanner.next();

				int customerId = customerAccount.validateLogin(emailId, password);

				if (customerId != 0) {
					System.out.println("Log in succesful");

					System.out.println("Enter 1 to deposit money ," + " Enter 2 to withdraw money,"
							+ " Enter 3 to view accountBalance," + " Enter 4 to transfer money");
					int option = scanner.nextInt();
					if (option == 1) {
						System.out.println("Enter Amount to Deposit");
						double amount = scanner.nextDouble();
						customerAccount.depositMoney(customerId, amount);
					}
					if (option == 2) {
						System.out.println("Enter Amount to withdraw");
						double amount = scanner.nextDouble();
						customerAccount.withdrawMoney(customerId, amount);
					}
					if (option == 3) {
						customerAccount.viewAccountBalance(customerId);
					}
					if (option == 4) {
						System.out.println("Enter account number that you want to transfer");
						String accountNumber = scanner.next();
						System.out.println("Enter amount that you want to transfer");
						double amount = scanner.nextDouble();
						customerAccount.moneyTransfer(customerId, accountNumber, amount);
					}
				} else {
					System.out.println("you are not an existing customer please create new account");
					customerAccount.createAccount();
				}

			} else if (choice == 2) {
				System.out.println("welcome to vasavi bank ,,you are new customer,,create your account");
				customerAccount.createAccount();

			} else {
				System.out.println("you did not choosen coreect option,,sorry for inconvience");
			}

			break;

		default:

			break;

		}

	}

}
