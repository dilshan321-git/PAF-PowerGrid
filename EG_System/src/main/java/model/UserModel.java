package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import conn.DB_Connect;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

	private int results;

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String get_User() {
		
		Connection connection;
		PreparedStatement prepared_statement;
		String table="";
		
		try {
			
			connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("SELECT * FROM users");
			
			ResultSet dataSet = prepared_statement.executeQuery();
			
			table = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;border-radius: 10px;'>ID</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Name</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Email</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Address</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Edit</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Delete</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (dataSet.next()) {
				
				table = table+"<tr><td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(4)+"</td>"
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
	
	public void add_User(String name,String email,String address) {
		
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("insert into users (name,email,address) values (?,?,?)");
			prepared_statement.setString(1, name);
			prepared_statement.setString(2, email);
			prepared_statement.setString(3, address);
			prepared_statement.execute();
			prepared_statement.close();
			connection.close();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void edit_User(int id,String name,String email,String address) {
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			
				prepared_statement = connection.prepareStatement("UPDATE users SET name=?,email=?,address=? where id=?");
				prepared_statement.setString(1, name);
				prepared_statement.setString(2, email);
				prepared_statement.setString(3, address);
				prepared_statement.setInt(4,id);
				prepared_statement.execute();
				prepared_statement.close();
				connection.close();
				setResults(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void delete_User(int id) {
		Connection connection;
		PreparedStatement prepared_statement;
		
		try {
			connection = DB_Connect.getDB();
			
			prepared_statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
			prepared_statement.setInt(1, id);
			prepared_statement.execute();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}
	
}
