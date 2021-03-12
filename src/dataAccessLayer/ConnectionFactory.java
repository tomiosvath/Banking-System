package dataAccessLayer;

import java.sql.*;
/**
 * this class establishes the connection between the application and the MySQL database as follows: 
 * it needs a driver, the database url, the user and the password. 
 * @author tomi
 *
 */
public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/bank1?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	//private static ConnectionFactory singleInstance = new ConnectionFactory();
	/**
	 * constructor that uses the forName() method in order to register the driver class
	 */
	private ConnectionFactory(){
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method that is used to establish the connection with the database
	 * @return connect, the connection with the database
	 */
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		try {
		connect = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	/**
	 * method that closes the connection with the database
	 * @param connect represents the connection with the database
	 */
	public static void close(Connection connect) {
		try {
			connect.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * method that closes a Statement
	 * @param st represents a Statement that can execute queries
	 */
	public static void close(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * method that closes a ResultSet
	 * @param rs represents a ResultSet that can obtain all the records of a table
	 */
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}