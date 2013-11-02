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
	
	public int update(String query) throws Exception{
		String[] arr = query.split(":");
		return this.prepareStatement(arr).executeUpdate();
	}
	
	public String select(String query) throws Exception{
		String[] arr = query.split(":");
		return this.getTableInString(this.prepareStatement(arr).executeQuery());
	}
	
	private PreparedStatement prepareStatement(String[] arr) throws Exception{
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(arr[0]);
		int i = 1;
		while(i < arr.length){
			pstmt.setString(i, arr[i]);
			i++;
		}
		return pstmt;
	}
	
	private String getTableInString(ResultSet rs) throws Exception{
		String ret = "";
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		while(rs.next()){
			for(int i = 1; i <= columnCount; i++){
				ret += rs.getString(i) + "\t";
			}
			ret = ret.trim();
			ret += "\n";
		}
		return ret;
	}
	
	public void close(){
		try{
			conn.close();
		}catch(Exception e){
			System.out.println("Failed to Close the connection.");
		}
	}
}
