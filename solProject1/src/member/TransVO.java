package member;

public class TransVO {

	private int accCode;
	private String state;
	private int price;
	private int balance;
	private String date;

	public TransVO(int accCode, String state, int price, int balance, String date) {
		super();
		this.accCode = accCode;
		this.state = state;
		this.price = price;
		this.balance = balance;
		this.date = date;
	}

	public int getAccCode() {
		return accCode;
	}

	public void setAccCode(int accCode) {
		this.accCode = accCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
