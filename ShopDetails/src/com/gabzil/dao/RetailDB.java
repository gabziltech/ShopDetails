package com.gabzil.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class RetailDB {

	
	public Connection getDBConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://1.8.7.198:3306/retaildb";
			//String connectionURL = "jdbc:mysql://localhost:3306/retail";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    //connection = DriverManager.getConnection(connectionURL, "root", "gabzil");
			connection = DriverManager.getConnection(connectionURL, "gabzil", "gabzil@123");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
}
