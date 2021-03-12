package model;

public class Customer {
	protected int customer_id;
	protected String name;
	protected String address;
	protected String email;
	protected String phone;
	protected String username;
	protected String password; 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Customer(int customer_id, String name, String address, String email, String phone) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	

}
