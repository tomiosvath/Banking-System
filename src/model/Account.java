package model;

public class Account {
	private String card_nr;
	private String expire_date;
	private int cvv;
	private int balance;
	private int idaccount;
	private int idcustomer;
	
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getCvv() {
		return cvv;
	}
	public int getBalance() {
		return balance;
	}
	public String getCard_nr() {
		return card_nr;
	}
	public void setCard_nr(String card_nr) {
		this.card_nr = card_nr;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	
	public int getIdaccount() {
		return idaccount;
	}
	public void setIdaccount(int idaccount) {
		this.idaccount = idaccount;
	}
	public int getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}
	public Account(String card_nr, String expire_date, int cvv, int balance, int idaccount, int idcustomer) {
		this.card_nr = card_nr;
		this.expire_date = expire_date;
		this.cvv = cvv;
		this.balance = balance;
		this.idaccount = idaccount;
		this.idcustomer = idcustomer;
	}
	
	public Account() {
		
	}
	

}
