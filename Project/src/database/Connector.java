package database;

import java.sql.*;

class Connector {
	private static final String CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/orenoturnda";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	
	private Connection conn;
	
	/**
	 * constructor that initiates the connection
	 */
	public Connector() {
		try{
			Class.forName(CLASS_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		}catch(Exception e){
			System.out.println("Failed to Connect to Database : " + URL);
		}
	}
	
	public void close(){
		try{
			conn.close();
		}catch(Exception e){
			System.out.println("Failed to Close the connection.");
		}
	}
}
