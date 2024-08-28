package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	
	Connection con;
	
	public void getDbconnection() throws SQLException
	{
		
		try {
		
			Driver driver = new Driver();
		DriverManager.registerDriver(driver);
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root", "admin");
		} 
		catch( Exception e)
		{
			
		}
			
	}
	
	public void closeDbconnection() throws SQLException
	{
		
		try {
		con.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public ResultSet executeConSelectQuery(String query) throws SQLException
	{
		ResultSet result = null;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);
		}
		catch(Exception e)
		{
	
		}
		return result;
	}
	
		public int executeNonSelectQuery(String query)
		{
			int result = 0;
			try
			{
				Statement stat = con.createStatement();
				result = stat.executeUpdate(query);
			}
			catch(Exception e)
			{
				
			}
			return result;
		}
  }


