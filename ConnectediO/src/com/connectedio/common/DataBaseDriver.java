package com.connectedio.common;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class DataBaseDriver {
	


		private Connection connect = null;
		
		private Statement statement = null;
//		private PreparedStatement preparedstatement = null ;
		private ResultSet resultSet = null;
		public ResultSet readDatabases(String entryname, String nameOfTable) {
		try { 

		Class.forName("com.mysql.jdbc.Driver");     // to the jdbc jar file
		connect= DriverManager.getConnection("jdbc:mysql://www.connectedio.com" , "usename","password");     //how to  	  	
		System.out.println("we are connected to the server");
		
		
		statement = connect.createStatement();
		resultSet= statement.executeQuery("select"+ entryname + "from" + nameOfTable);
		
		
		
		} catch(Exception e) {
			System.out.println("Unable to Connect");
			e.printStackTrace();
		}
	return resultSet;
		}
			
	}


