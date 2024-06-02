package oracleDB;

import java.sql.*;

public class DB_Methods {
	static Connection conn;

	public static void main(String args[]) {
		DB instance = new DB();

		instance.connect();

		conn = instance.conn;
		System.out.println(conn);

//		insertCustomer("c0011", "ANWESHA DAS", 9999999999L, "BHUB");
//		insertCustomer("C0012", "SACHIN SINGH", 9898989898L, "CTC");
//		insertCustomer("C0013", "ARJUN MISHRA", 7777777777L, "BBSR");
//		
//		deleteCustomer("C0013");
//		
//		updateCustomerName("C0012", "ANUSHKA ANTRA");
//		updateCustomerPhone("C0012", 5689226686l);
//		updateCustomerCity("C0012", "CTC");
//		
//		String table[][] = displayCustomer();

		try {
//			displayLoanDetails("C0009");
//			withdrawMoney("A0004", 10000l);
//			displayAccountDetailsAccNo("A0004");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		instance.close();

	}
	
	
	static void depositMoney(String account_no, long amount) throws Exception{
		PreparedStatement ps = conn.prepareStatement("UPDATE account SET balance=balance+? WHERE account_no=?");
		ps.setLong(1, amount);
		ps.setString(2, account_no);
		
		int count = ps.executeUpdate();
		if(count > 0) {
			System.out.println(count + " rows updated");
		}else {
			System.out.println("faild");
		}
		
	}
	
	static void withdrawMoney(String account_no, long amount) throws Exception{
		PreparedStatement ps = conn.prepareStatement("SELECT balance FROM account WHERE account_no=?");
		ps.setString(1, account_no);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		long balance = rs.getLong(1);
		System.out.println(balance);
		
		if(balance >= amount) {
			PreparedStatement ps2 = conn.prepareStatement("UPDATE account SET balance=balance-? WHERE account_no=?");
			ps2.setLong(1, amount);
			ps2.setString(2, account_no);
			
			int count = ps2.executeUpdate();
			if(count > 0) {
				System.out.println(count + " rows updated");
			}else {
				System.out.println("faild");
			}
			
		}else {
			System.out.println("Not enough balance");
		}
	}

	static void displayLoanDetails(String cust_no) throws Exception {

		PreparedStatement ps = conn.prepareStatement("SELECT c.cust_no, c.name, c.phone_no, c.city, "
				+ "l.loan_no, l.amount, "
				+ "b.branch_code, b.branch_name, b.branch_city FROM customer c "
				+ "JOIN loan l ON c.cust_no=l.cust_no "
				+ "JOIN branch b on l.branch_code=b.branch_code "
				+ "WHERE c.cust_no=?");

		ps.setString(1, cust_no);

		ResultSet rs = ps.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();
		int col = metaData.getColumnCount();

		for (int i = 0; i < col; i++) {
			System.out.printf("%-20S ", metaData.getColumnName(i + 1));
		}
		System.out.println();

		while (rs.next()) {
			for (int i = 0; i < col; i++) {
				System.out.printf("%-20S ", rs.getString(i + 1));
			}
			System.out.println();
		}
	}

	static void displayAccountDetailsCustNo(String cust_no) throws Exception {

		PreparedStatement ps = conn.prepareStatement("SELECT c.cust_no, c.name, c.phone_no, c.city, a.account_no, "
				+ "a.type, a.balance, " 
				+ "b.branch_code, b.branch_city FROM customer c "
				+ "JOIN depositor d ON c.cust_no=d.cust_no " 
				+ "JOIN account a ON d.account_no=a.account_no "
				+ "JOIN branch b on a.branch_code=b.branch_code " 
				+ "WHERE c.cust_no=?");

		ps.setString(1, cust_no);

		ResultSet rs = ps.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();
		int col = metaData.getColumnCount();

		for (int i = 0; i < col; i++) {
			System.out.printf("%-20S ", metaData.getColumnName(i + 1));
		}
		System.out.println();

		while (rs.next()) {
			for (int i = 0; i < col; i++) {
				System.out.printf("%-20S ", rs.getString(i + 1));
			}
			System.out.println();
		}

	}

	static void displayAccountDetailsAccNo(String account_no) throws Exception {

		PreparedStatement ps = conn.prepareStatement("SELECT c.cust_no, c.name, c.phone_no, c.city, a.account_no, "
				+ "a.type, a.balance, " + "b.branch_code, b.branch_city FROM customer c "
				+ "JOIN depositor d ON c.cust_no=d.cust_no " + "JOIN account a ON d.account_no=a.account_no "
				+ "JOIN branch b on a.branch_code=b.branch_code " + "WHERE a.account_no=?");

		ps.setString(1, account_no);

		ResultSet rs = ps.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();
		int col = metaData.getColumnCount();

		for (int i = 0; i < col; i++) {
			System.out.printf("%-20S ", metaData.getColumnName(i + 1));
		}
		System.out.println();

		while (rs.next()) {
			for (int i = 0; i < col; i++) {
				System.out.printf("%-20S ", rs.getString(i + 1));
			}
			System.out.println();
		}

	}

	static void updateCustomerName(String cust_no, String name) {

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE customer SET name=? WHERE cust_no=?");
			ps.setString(1, name);
			ps.setString(2, cust_no);

			int count = ps.executeUpdate();

			if (count > 0) {
				System.out.println("Update successfully");
			}

		} catch (SQLException e) {
			System.out.println("Error in updating customer name");
			e.printStackTrace();
		}

	}

	static void updateCustomerPhone(String cust_no, long phone) {

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE customer SET phone_no=? WHERE cust_no=?");
			ps.setLong(1, phone);
			ps.setString(2, cust_no);

			int count = ps.executeUpdate();

			if (count > 0) {
				System.out.println("Update successfully");
			}

		} catch (SQLException e) {
			System.out.println("Error in updating phone no");
			e.printStackTrace();
		}

	}

	static void updateCustomerCity(String cust_no, String city) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE customer SET city=? WHERE cust_no=?");
			ps.setString(1, city);
			ps.setString(2, cust_no);

			int count = ps.executeUpdate();

			if (count > 0) {
				System.out.println("Update successfully");
			}

		} catch (SQLException e) {
			System.out.println("Error in updating customer name");
			e.printStackTrace();
		}
	}

	static void deleteCustomer(String cust_no) {

		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM customer WHERE cust_no=?");
			ps.setString(1, cust_no);
			int count = ps.executeUpdate();

			if (count > 0) {
				System.out.println(count + " row deleted");
			} else {
				System.out.println("no row deleted");
			}

		} catch (SQLException e) {
			System.out.println("Error occured in deleting row");
			e.printStackTrace();
		}

	}

	static void insertCustomer(String cust_no, String name, long phone, String city) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
			ps.setString(1, cust_no);
			ps.setString(2, name);
			ps.setLong(3, phone);
			ps.setString(4, city);

			int count = ps.executeUpdate();
			if (count > 0) {
				System.out.println("Row Inserted");
			}

		} catch (SQLException e) {
			System.out.println("Error in insert method");
			e.printStackTrace();
		}
	}

	static String[][] displayCustomer() {
		String[][] table = null;

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer");

			ResultSet rs = ps.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int col = metaData.getColumnCount();

			int row = getRows("customer");
			System.out.println(row + " " + col);

			table = new String[row + 1][col];

			for (int j = 1; j <= col; j++) {
				table[0][j - 1] = metaData.getColumnName(j);
			}

			int r = 1;
			while (rs.next()) {

				for (int j = 1; j <= col; j++) {
					table[r][j - 1] = rs.getString(j);
				}
				r++;
			}

			// display the table
			for (int i = 0; i <= row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print(table[i][j] + "       ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("Error in display customer method");
			e.printStackTrace();
		}

		return table;
	}

	static int getRows(String table) {
		int row = 0;

		try {

			ResultSet rs = conn.prepareStatement("SELECT COUNT(*) FROM " + table).executeQuery();
			rs.next();
			row = rs.getInt(1);
			rs.close();

		} catch (SQLException e) {
			System.out.println("Error in counting rows");
			e.printStackTrace();
		}

		return row;
	}
}
