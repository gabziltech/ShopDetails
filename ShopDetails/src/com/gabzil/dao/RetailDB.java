package com.gabzil.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class RetailDB {

	
	public Connection getDBConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://127.0.0.1:3306/retail";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//connection = DriverManager.getConnection(connectionURL, "root", "root");
			connection = DriverManager.getConnection(connectionURL, "root", "gabzil");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

	}
}
