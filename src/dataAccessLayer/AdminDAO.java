package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;

public class AdminDAO extends AbstractDAO<Admin> {
	
	 public String authenticateUser(Admin c) {
		 String username = c.getUsername(); //Keeping user entered values in temporary variables.
		 String password = c.getPassword();
		 String usernameDB = "";
		 String passwordDB ="";
		 ResultSet resultSet = null;
		 Connection con = ConnectionFactory.getConnection();
		 try {
			 String query = "select username, password from admin";
			 PreparedStatement st = con.prepareStatement(query);
			 resultSet = st.executeQuery();
			 while(resultSet.next()) {
				 usernameDB = resultSet.getString("username");
				 passwordDB = resultSet.getString("password");
				 if(username.equals(usernameDB) && password.equals(passwordDB)) {
					 return "success";
				 }
			 }
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return "invalid username or password";
	 }
	 
	 public String selectTransactionHistory() {
			ResultSet rs = null;
			String all = "";
			Connection con = ConnectionFactory.getConnection();
			try {
				String query = "select * from bank1.transactions";
				PreparedStatement st;
				st = con.prepareStatement(query);
				rs = st.executeQuery();
				while(rs.next()) {
					String date =  rs.getString("date");
					String amount = rs.getString("amount");
					String rec = rs.getString("receiver");
					all += "Date: " + date+", amount: "+amount+", receiver: "+rec+"<br> <br>";
				}
				
				con.close();
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "error";
		}

}
