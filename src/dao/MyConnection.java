package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection con = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvcproject", "root", "root");
				System.out.println("Connecting...");
			} catch (SQLException e) {
				System.out.println("Database not found");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not found");
		}
		return con;
	}
}
