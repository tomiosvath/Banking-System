package dataAccessLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Customer;
/**
 * this class extends the AbstractDAO class and can implement all of its methods.
 * @author tomi
 *
 */
public class CustomerDAO extends AbstractDAO<Customer> {
	/**
	 * method that always uses the inherited delete method to delete by the customer_id field
	 */
	public String del(String us) {
		String res = super.delete("username", us);
		return res;
	}
	/**
	 * inserts the following list of parameters (a new reccord) in the customers table FOR REGISTER OPERATION
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param f
	 * @param g
	 * @param h
	 */
	public String insert(String a, String b, String c, String d, String e, String f) {
		Connection con = ConnectionFactory.getConnection();
		try {
			String query = " insert into bank1.customer (customer_id, name, address, email, phone, username, password) values(NULL, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3, c);
			st.setString(4, d);
			st.setString(5, e);
			st.setString(6, f);
			st.execute();
			con.close();
			return "success";
		} catch(SQLException exc) {
			System.out.println("error");
			exc.printStackTrace();
		}
		return "nothin";
	}
	
	public String adminAdd(int need, String g, String h, int i, int j) {
		Connection con = ConnectionFactory.getConnection();
		try {
			String query1 = " insert into bank1.account (idaccount, idcustomer, card_nr, expire_date, cvv, balance) values(NULL, ?, ?, ?, ?, ?) ";
			PreparedStatement st1 = con.prepareStatement(query1);
			st1.setInt(1, need);//get
			st1.setString(2, g);
			st1.setString(3, h);
			st1.setInt(4, i);
			st1.setInt(5, j);
			st1.execute();
			con.close();
			return "Customer created successfully!";
		} catch(SQLException exc) {
			System.out.println("error");
			exc.printStackTrace();
		}
		return "An error occured trying to add the user, please try again.";
	}
	
	 public String authenticateUser(Customer c) {
		 String username = c.getUsername(); //Keeping user entered values in temporary variables.
		 String password = c.getPassword();
		 String usernameDB = "";
		 String passwordDB ="";
		 ResultSet resultSet = null;
		 Connection con = ConnectionFactory.getConnection();
		 try {
			 String query = "select username, password from customer";
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
		 return "invalid username or password!";
	 }
	 
	 //customer a is authenticated, tries to send to customer b
	 public String makeTransaction(Customer a, Customer b, Account froma, Account fromb, int amount) {
		 String usernameb = b.getUsername();
		 String usernamebDB = "";
		 
		 ResultSet rs = null;
		 Connection con = ConnectionFactory.getConnection();
		 try {
			 String query = "select username from customer";
			 PreparedStatement st = con.prepareStatement(query);
			 rs = st.executeQuery();
			 while(rs.next()) {
				 usernamebDB = rs.getString("username");
				 if(usernameb.equals(usernamebDB)) {
					 String q = "update bank1.account set balance=? where idaccount=?";
					 PreparedStatement st1 = con.prepareStatement(q);
					 st1.setInt(1, froma.getBalance() - amount);
					 st1.setInt(2, froma.getIdaccount());
					 st1.execute();
					 
					 PreparedStatement st2 = con.prepareStatement(q);
					 st2.setInt(1, fromb.getBalance() + amount);
					 st2.setInt(2, fromb.getIdaccount());
					 st2.execute();
					 
					 String qu = " insert into bank1.transactions (idtransactions, account_id, date, amount, receiver) values(NULL, ?, ?, ?, ?) ";
					 PreparedStatement st3 = con.prepareStatement(qu);
				 	 st3.setInt(1, froma.getIdaccount());
				   	 st3.setString(2, "2020-01-17");
					 st3.setInt(3, amount);
					 st3.setString(4, b.getUsername());
					 st3.execute();
					 return "success";
					 //con.close();
					 
				 }
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return "One of the usernames is not correct, please try again";
	 }
	
	public int selectBalanceFromCustomerId(int a) {
		ResultSet rs = null;
		Connection con = ConnectionFactory.getConnection();
		try {
			String query = "select balance from account where idcustomer=?";
			PreparedStatement st;
			st = con.prepareStatement(query);
			st.setInt(1, a);
			rs = st.executeQuery();
			while(rs.next())
				return rs.getInt("balance");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int selectIdaccountFromCustomerId(int a) {
		ResultSet rs = null;
		Connection con = ConnectionFactory.getConnection();
		try {
			String query = "select idaccount from account where idcustomer=?";
			PreparedStatement st;
			st = con.prepareStatement(query);
			st.setInt(1, a);
			rs = st.executeQuery();
			while(rs.next())
				return rs.getInt("idaccount");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int selectCustomerIdFromUsername(String username) {
		ResultSet rs = null;
		Connection con = ConnectionFactory.getConnection();
		try {
			String query = "select customer_id from customer where username=?";
			PreparedStatement st;
			st = con.prepareStatement(query);
			st.setString(1, username);
			rs = st.executeQuery();
			while(rs.next())
				return rs.getInt("customer_id");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String selectTransactionHistoryFromAccountId(int a) {
		ResultSet rs = null;
		String all = "";
		Connection con = ConnectionFactory.getConnection();
		try {
			String query = "select * from bank1.transactions where account_id=?";
			PreparedStatement st;
			st = con.prepareStatement(query);
			st.setInt(1, a);
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
	
	public static void main(String args[]) {
		Customer a = new Customer();
		a.setUsername("laurap");
		a.setPassword("lp");
		
		CustomerDAO cc = new CustomerDAO();
		
		//int balancea = cc.sel(nr);
		//froma.setBalance(balancea);
		String p = cc.del("feteleee");
		System.out.println(p);
		//cc.makeTransaction(a, b, froma, fromb, 100);
	}
	
}