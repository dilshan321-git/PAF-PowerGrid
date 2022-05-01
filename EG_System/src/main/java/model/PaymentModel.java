package model;

import java.sql.Connection;
import conn.DB_Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

	private int results;

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String get_Payment() {
		
		Connection connection;
		PreparedStatement prepared_statement;
		String table="";
		
		try {
			
			connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("SELECT * FROM payment");
			
			ResultSet dataSet = prepared_statement.executeQuery();
			
			table = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;border-radius: 10px;'>ID</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Payment Method</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Address</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>NIC</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Amount</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Edit</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Delete</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (dataSet.next()) {
				
				table = table+"<tr><td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(5)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'><button type='button'>Edit</button></td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'><button type='button'>Delete</button></td>"
					  + "</tr>";
				
			}
			
			prepared_statement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return table+"</table>";
	}
	
	public void add_Payment(String payment_method,String address,String nic,double amount) {
		
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("insert into payment (payment_method, address, nic, amount) values (?,?,?,?)");
			prepared_statement.setString(1, payment_method);
			prepared_statement.setString(2, address);
			prepared_statement.setString(3, nic);
			prepared_statement.setDouble(4, amount);
			prepared_statement.execute();
			prepared_statement.close();
			connection.close();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void edit_Payment(int id,String payment_method,String address,String nic,double amount) {
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			
				prepared_statement = connection.prepareStatement("UPDATE payment SET payment_method=?, address=?, nic=?, amount=? where id=?");
				prepared_statement.setString(1, payment_method);
				prepared_statement.setString(2, address);
				prepared_statement.setString(3, nic);
				prepared_statement.setDouble(4, amount);
				prepared_statement.setInt(5,id);
				prepared_statement.execute();
				prepared_statement.close();
				connection.close();
				setResults(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void delete_Payment(int id) {
		Connection connection;
		PreparedStatement prepared_statement;
		
		try {
			connection = DB_Connect.getDB();
			
			prepared_statement = connection.prepareStatement("DELETE FROM payment WHERE id=?");
			prepared_statement.setInt(1, id);
			prepared_statement.execute();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}
	
}
