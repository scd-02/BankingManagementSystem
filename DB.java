package oracleDB;

import java.sql.*;

public class DB {
	
	Connection conn;
	
	void connect() {
		try {
			// load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "system", "<password>");
			System.out.println("Connection Established with oracle server");
			
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
