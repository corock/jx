package com.corock.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {		
		Connection conn = null;
		
		try {
			// Step 1. load JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Step 2. connect
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("Connected successfully!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
