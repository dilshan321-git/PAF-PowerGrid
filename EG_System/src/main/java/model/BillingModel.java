package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import conn.DB_Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BillingModel {

	private int results;

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String get_Billing() {
		
		Connection connection;
		PreparedStatement prepared_statement;
		String table="";
		
		try {
			
			connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("SELECT * FROM billing");
			
			ResultSet dataSet = prepared_statement.executeQuery();
			
			table = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;border-radius: 10px;'>ID</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Customer Name</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Bill Type</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Description</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Date</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Total</th>"
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
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(6)+"</td>"
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
	
	public void add_Billing(String cname,String type,String description,String date,double total) {
		
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("insert into billing (cname, type, description, date, total) values (?,?,?,?,?)");
			prepared_statement.setString(1, cname);
			prepared_statement.setString(2, type);
			prepared_statement.setString(3, description);
			prepared_statement.setString(4, date);
			prepared_statement.setDouble(5, total);
			prepared_statement.execute();
			prepared_statement.close();
			connection.close();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void edit_Billing(int id,String cname,String type,String description,String date,double total) {
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			
				prepared_statement = connection.prepareStatement("UPDATE billing SET cname=?, type=?, description=?, date=?, total=? where id=?");
				prepared_statement.setString(1, cname);
				prepared_statement.setString(2, type);
				prepared_statement.setString(3, description);
				prepared_statement.setString(4, date);
				prepared_statement.setDouble(5, total);
				prepared_statement.setInt(6,id);
				prepared_statement.execute();
				prepared_statement.close();
				connection.close();
				setResults(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void delete_Billing(int id) {
		Connection connection;
		PreparedStatement prepared_statement;
		
		try {
			connection = DB_Connect.getDB();
			
			prepared_statement = connection.prepareStatement("DELETE FROM billing WHERE id=?");
			prepared_statement.setInt(1, id);
			prepared_statement.execute();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}
	
}
