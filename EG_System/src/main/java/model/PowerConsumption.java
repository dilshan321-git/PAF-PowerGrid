package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import conn.DB_Connect;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class PowerConsumption {

	private int results;

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String get_Power() {
		
		Connection connection;
		PreparedStatement prepared_statement;
		String table="";
		
		try {
			
			connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("SELECT * FROM power");
			
			ResultSet dataSet = prepared_statement.executeQuery();
			
			table = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;border-radius: 10px;'>ID</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Usage</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Unit Type</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Description</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Edit</th>"
	                +"<th style='border: 1px solid black;border-radius: 10px;'>Delete</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (dataSet.next()) {
				
				
				String button = "<button type='button1' onclick='edit("+dataSet.getString(1)+")' class='btn btn-primary'>Edit</button> <button type='button' onclick='deletes("+dataSet.getString(1)+")' class='btn btn-warning'>Delete</button>";
				
				table = table+"<tr><td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;border-radius: 10px;'>"+dataSet.getString(4)+"</td>"		
						
						+ "<td >" +button+ "</td>"
					  + "</tr>";
				
			}
			
			prepared_statement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return table+"</table>";
	}
	
	public void add_Power(int usage,String unit_type,String description) {
		
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			prepared_statement = connection.prepareStatement("insert into power (usages,unit_type,description) values (?,?,?)");
			prepared_statement.setInt(1, usage);
			prepared_statement.setString(2, unit_type);
			prepared_statement.setString(3, description);
			prepared_statement.execute();
			prepared_statement.close();
			connection.close();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
	}

	public void edit_Power(int id,int usage,String unit_type,String description) {
		PreparedStatement prepared_statement;
		
		try {
			Connection connection = DB_Connect.getDB();
			
				prepared_statement = connection.prepareStatement("UPDATE power SET usages=?,unit_type=?,description=? where id=?");
				prepared_statement.setInt(1, usage);
				prepared_statement.setString(2, unit_type);
				prepared_statement.setString(3, description);
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

	public void delete_Power(int id) {
		Connection connection;
		PreparedStatement prepared_statement;
		
		try {
			connection = DB_Connect.getDB();
			
			prepared_statement = connection.prepareStatement("DELETE FROM power WHERE id=?");
			prepared_statement.setInt(1, id);
			prepared_statement.execute();
			setResults(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setResults(0);
		}
		
	}
		public JSONObject getOnePower(int id) throws JSONException {
			Connection connection;
			PreparedStatement preparedStatement;
			JSONObject json = new JSONObject();
			
			try {
				connection = DB_Connect.getDB();
				
				preparedStatement = connection.prepareStatement("SELECT * FROM power where id=?");
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				
		

				while(rs.next())
				{
					json.put("id", rs.getString(1));
					json.put("usage", rs.getString(2));
					json.put("unit_type", rs.getString(3));
					json.put("description", rs.getString(4));
					
				}
				
			}catch (ClassNotFoundException | SQLException  e) {
				setResults(0);
			}
			return json;
		}




}
	

