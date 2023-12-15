
import java.util.*;
public class Bank {
	public ArrayList<Account> account = new ArrayList<Account>();
	
	void openAccount(int id, String fname, String lname, Date dob, 
	String ssn, double balance, double overdraft, String type) {
		account.add(new Account(id, fname, lname, dob, ssn, balance, overdraft, type));
	}
	int findAccount(int accountNumber) {
		for(int i = 0; i < account.size(); i++) {
			if(accountNumber == account.get(i).getId()) {
				return i;
			}
		}
		return 0;
	}
	boolean doesAccountExist(int accountId) {
		for(int i = 0; i < account.size(); i++) {
			if(accountId == account.get(i).getId()) {
				return true;
			}
		}
		return false;
	}
}
