
public class Transaction {
	private int id;
	private double transaction;
	private String type;
	Transaction(int id, double transaction, String type){
		this.id = id;
		this.transaction = transaction;
		this.type = type;
	}
	public String toString() {
		String s = id+" : "+type+" : "+transaction;
		return s;
	}
}
