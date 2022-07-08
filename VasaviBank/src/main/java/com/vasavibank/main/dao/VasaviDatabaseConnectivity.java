
package com.vasavibank.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VasaviDatabaseConnectivity {
	public static  Connection getConnection()
	{

		String url="jdbc:mysql://localhost:3306/vasavibanking";
		String user="root";
		String password="R@ghava1";
		Connection con=null;
		try {
		 con=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
		
	}

}
