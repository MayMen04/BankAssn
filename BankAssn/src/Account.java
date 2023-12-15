

import java.util.*;

public class Account {
	private String status;
	private int id;
	private int transactionId = 100;
	private String fname;
	private String lname;
	private Date dob;
	private String ssn;
	private int age;
	private double balance;
	private double overdraftLimit;
	private String type;
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	Account(int id, String fname, String lname, Date dob, String ssn, double balance, double overdraft, String type){
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.ssn = ssn;
		this.balance = balance;
		this.overdraftLimit = overdraft;
		this.type = type;
		status = "open";
	}
	public void deposit(double deposit) {
		boolean check = false;
		double holder = 0;
		if(balance < 0) {
			check = true;
			holder = -balance;
		}
		balance += deposit;
		if(check) {
			overdraftLimit += holder;
		}	
		setTransaction(deposit, "debit");
		transactionId++;
	}
	public void withdraw(double withdraw) {
		balance -= withdraw;
		if(balance < 0) {
			overdraftLimit += balance;
		}
		setTransaction(withdraw, "credit");
		transactionId++;
	}
	public void setTransaction(double transaction, String type) {
		transactions.add(new Transaction(transactionId, transaction, type));
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public double getBalance() {
		return balance;
	}
	public double getOverdraft() {
		return overdraftLimit;
	}
	public String getStatus() {
		return status;
	}
	public String toString() {
		String s = id+"("+type+") : "+fname+" : "+lname+" : "+ssn+" : "+balance+" : Status - "+status;
		return s;
	}
}
