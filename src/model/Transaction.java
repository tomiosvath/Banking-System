package model;

public class Transaction {
	private int idtransactions;
	private int account_id;
	private String date;
	private String receiver;
	private int amount;
	
	public Transaction() {
		
	}
	
	public int getIdtransactions() {
		return idtransactions;
	}
	public void setIdtransactions(int idtransactions) {
		this.idtransactions = idtransactions;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
