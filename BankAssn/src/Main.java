
import java.util.*;
public class Main {
	final public static Bank bank = new Bank();
	public static int id = 1000;
	public static void main(String[] args) {
		start();
	}
	static void start() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome To Bank");
		int startChoice = 0;
		while(true) {
			System.out.print("1 - Open Checking Account\n"
					+ "2 - Open Saving Account\n"
					+ "3 - List Account\n"
					+ "4 - Account Statement\n"
					+ "5 - Deposit Funds\n"
					+ "6 - Withdraw Funds\n"
					+ "7 - Close Account\n"
					+ "8 - Exit\n"
					+ "Enter Choice Here: ");
			if(input.hasNextInt()) {
				startChoice = input.nextInt();
				if(startChoice>=1&&startChoice<=8) {
					break;
				}
			}
			input.nextLine();
			System.out.println("\nInvalid Choice\n");
		}	
		System.out.println();
		switch(startChoice) {
		case 1: 
			openAccount("checkings");
			break;
		case 2: 
			openAccount("savings");
			break;
		case 3: 
			
			int amount = bank.account.size();
			System.out.println("Number Of Accounts - "+amount);
			for(int i = 0; i < amount; i++) {
				System.out.println(bank.account.get(i));
			}
			System.out.println();
			start();
			break;
		case 4: 
			accountStatement();
			break;
		case 5: 
			deposit();
			break;
		case 6:
			withdraw();
			break;
		case 7: 
			closeAccount();
			break;
		case 8:
			System.out.println("\nThank you for using the program");
			System.exit(0);
			break;
		}
		
	}
	static void closeAccount() {
		Scanner input = new Scanner(System.in);
		int accountId = 0;
		int accountNumber = 0;
		double depositAmount = 0;
		while(true) {
			try {
				System.out.print("Enter Account Id: ");
				accountId = input.nextInt();
				if(bank.doesAccountExist(accountId)) {
					accountNumber = bank.findAccount(accountId);
					bank.account.get(accountNumber).setStatus("closed");;
					break;
				}
				else {
					System.out.println("\nAccount Not Found\n");
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("\nEnter A Number\n");
			}
		}
		System.out.println();
		start();
	}
	static void deposit() {
		Scanner input = new Scanner(System.in);
		int accountId = 0;
		int accountNumber = 0;
		double depositAmount = 0;
		while(true) {
			try {
				System.out.print("Enter Account Id: ");
				accountId = input.nextInt();
				if(bank.doesAccountExist(accountId)) {
					accountNumber = bank.findAccount(accountId);
					
					break;
				}
				else {
					System.out.println("\nAccount Not Found\n");
					start();
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("\nEnter A Number\n");
			}
		}
		
		double balance = bank.account.get(accountNumber).getBalance();
		String status = bank.account.get(accountNumber).getStatus();
		while(true) {
			try {
				System.out.print("Enter Deposit Amount: ");
				depositAmount = input.nextDouble();
				Validation DepoEx = new Validation(depositAmount, balance);
				if(status == "open") {
					bank.account.get(accountNumber).deposit(depositAmount);
					System.out.println("Deposit Succeful, New Balance: "+bank.account.get(accountNumber).getBalance());
					break;
				}
				else {
					if(DepoEx.closedDepositValid()) {
						bank.account.get(accountNumber).deposit(depositAmount);
						break;
					}
					else {
						System.out.println("\nDeposit Failed Account Closed\n");
						start();
					}
				}
			}
			catch(InputMismatchException e) {
				System.out.println("\nEnter A Number\n");
			}
		}
		System.out.println();
		start();
	}
	static void withdraw() {
		Scanner input = new Scanner(System.in);
		int accountId = 0;
		int accountNumber = 0;
		double withdrawAmount = 0;
		while(true) {
			try {
				System.out.print("Enter Account Id: ");
				accountId = input.nextInt();
				if(bank.doesAccountExist(accountId)) {
					accountNumber = bank.findAccount(accountId);
					break;
				}
				else {
					System.out.println("\nAccount Not Found\n");
					start();
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("\nEnter A Number\n");
			}
		}
		double balance = bank.account.get(accountNumber).getBalance();
		double overdraft = bank.account.get(accountNumber).getOverdraft();
		String status = bank.account.get(accountNumber).getStatus();
		
		while(true) {
			try {
				System.out.print("Enter Withdraw Amount: ");
				withdrawAmount = input.nextDouble();
				Validation we = new Validation(withdrawAmount, balance, overdraft);
				if(status == "open") {
					if(we.openWithdrawValid()) {
						bank.account.get(accountNumber).withdraw(withdrawAmount);
						System.out.println("Withdrawal Succeful, New Balance: "+bank.account.get(accountNumber).getBalance());
						break;
					}	
					else {
						System.out.println("\nWithdrawal Failed - Overdraft Limit Exceeded\n");
						start();
					}
				}
				else {
					if(we.closedWithdrawValid()) {
						bank.account.get(accountNumber).withdraw(withdrawAmount);
						break;
					}
					else {
						System.out.println("\nWithdrawal Failed - Account Closed\n");
						start();
					}
				}
			}
			catch(InputMismatchException e) {
				System.out.println("\nEnter A Number\n");
			}
		}
		System.out.println();
		start();
	}
	static void accountStatement() {
		Scanner input = new Scanner(System.in);
		int accountNumber;
		int accountId = 0;
		while(true) {
			try {
				System.out.print("Enter Account Id: ");
				accountId = input.nextInt(); 
				if(bank.doesAccountExist(accountId)) {
					accountNumber = bank.findAccount(accountId);
					for(int i = 0; i < bank.account.get(accountNumber).transactions.size(); i++) {
						System.out.println(bank.account.get(accountNumber).transactions.get(i));
					}
					System.out.println("Balance: "+ bank.account.get(accountNumber).getBalance());
					break;
				}
				else {
					System.out.println("Account Doesn't Exist");
				}				
			}
			catch(Exception e) {
				System.out.println("Invalid");
			}
			start();
			input.nextLine();
		}
	}
	static void openAccount(String type) {
		Scanner input = new Scanner(System.in);
		String fname;
		String lname;
		String dob;
		Date dDob;
		long age;
		String ssn;
		double balance;
		double overdraft = 0;
		while(true) {
			System.out.print("Enter First Name: ");
			fname = input.next();
			Validation ne = new Validation(fname);
			if(ne.isNameValid()) {
				break;
			}
			else {
				System.out.println("\nInvaid Name\n");
			}
		}
		while(true) {
			System.out.print("Enter Last Name: ");
			lname = input.next();
			Validation ne = new Validation(lname);
			if(ne.isNameValid()) {
				break;
			}
			else {
				System.out.println("\nInvaid Name\n");
			}
		}
		while(true) {
			System.out.print("Enter Social Security: ");
			ssn = input.next();
			Validation se = new Validation(ssn);
			if(se.isSsnValid()) {
				break;
			}
			else {
				System.out.println("\nPlease Enter The 10 Digits Of The SNN\n");
			}
		}
		while(true) {
			System.out.print("Enter Date Of Birth (mm-dd-yy): ");
			dob = input.next();
			Validation de = new Validation(dob);
			if(de.isDateValid()) {
				age = de.getAge();
				dDob = de.getDob();
				break;
			}
			else {
				System.out.println("\nEnter In (mm-dd-yy) Format\n");
			}	
		}
		if(type == "checkings") {
			if(age < 16) {
				System.out.println("\nOpen Checkings Account Failed - Age Under 16\n");
				start();
			}
		}
		else {
			if(age < 5) {
				System.out.println("\nOpen Savings Account Failed - Age Under 5\n");
				start();
			}
		}
		while(true) {
			System.out.print("Enter Balance: ");
			try {
				balance = input.nextDouble();
				if(balance >= 0) {
					break;
				}	
				else {
					System.out.println("\nNumber Must Be Positive\n");
				}
			}
			catch(Exception e) {
				System.out.println("\nInvalid Number\n");
			}
			input.nextLine();
		}
		if(type == "checkings") {
			if(age > 18) {
				while(true) {
					System.out.print("Enter Overdraft Limit: ");
					try {
						overdraft = input.nextDouble();
						if(overdraft >= 0) {
							break;
						}	
						else {
							System.out.println("\nNumber Must Be Positive\n");
						}
					}
					catch(Exception e) {
						System.out.println("\nInvalid Number\n");
					}
					input.nextLine();
				}
			}
		}
		System.out.println();
		bank.openAccount(id, fname, lname, dDob, ssn, balance, overdraft, type);
		id++;
		start();
	}
}