

import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
	private double amount;
	private double balance;
	private double overdraft;
	private String holder;
	private Date date;
	private Date today = new Date();
	private long age;
	public Validation(double deposit, double balance){
		this.amount = deposit;
		this.balance = balance;
	}
	public Validation(double withdraw, double balance, double overdraft) {
		this.amount = withdraw;
		this.balance = balance;
		this.overdraft = overdraft;
	}
	public Validation(String holder) {
		this.holder = holder;
	}
	//Deposit
	public boolean closedDepositValid() {
		double delta = amount + balance;
		if(delta <= 0) {
			return true;
		}
		return false;
	}
	//Withdrawal
	public boolean openWithdrawValid() {
		double check = balance + overdraft;
		double delta = check - amount;
		if(delta >= 0) {
			return true;
		}
		return false;
	}
	public boolean closedWithdrawValid() {
		double delta = balance - amount;
		if(delta >= 0) {
			return true;
		}
		return false;
	}
	//Name
	public boolean isNameValid() {
		int counter = 0;
		if(holder.length() >= 3) {
			counter++;
			if(Character.isUpperCase(holder.charAt(0))) {
				counter++;
			}
		}
		if(counter == 2) {
			return true;
		}
		return false;
	}
	//SSN
	public boolean isSsnValid() {
		int count = 0;
		if(holder.length() == 10) {
			for(int i = 0; i < 10; i++) {
				if(holder.charAt(i) < '0' || holder.charAt(i) > '9') {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public boolean isDateValid() {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yy");
		try {
			date = df.parse(holder);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public Date getDob() {
		return date;
	}
	public long getAge() {
		long dateMs = date.getTime();
		long todayMs = today.getTime();
		long delta = todayMs - dateMs;
		age = delta/1000/60/60/24/365;
		return age;
	}
}
