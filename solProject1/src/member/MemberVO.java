package member;

public class MemberVO extends LoginVO {

	private int accCode;
	private int[] coinArr;
	
	public MemberVO() {}
	
	public MemberVO(String id, String pw, String name, String phone) {
		super(id, pw, name, phone);
	}
	
	public MemberVO(String id, String pw, String name, String phone, int accCode) {
		super(id, pw, name, phone);
		setAccCode(accCode);
	}
	public MemberVO(String id, String pw, String name, String phone, int accCode, int[] coinArr) {
		super(id, pw, name, phone);
		setAccCode(accCode);
		setCoinArr(coinArr);
	}

	public int getAccCode() {
		return accCode;
	}

	public void setAccCode(int accCode) {
		this.accCode = accCode;
	}

	public int[] getCoinArr() {
		return coinArr;
	}

	public void setCoinArr(int[] coinArr) {
		this.coinArr = coinArr;
	}

	public String printCoinEA() {
		String msg = "보유 코인은\n500chip : " + coinArr[0] + "\n100chip : " + coinArr[1] + "\n50chip : " + coinArr[2] + "\n10chip : " + coinArr[3] + "개 입니다.";
		return msg;
	}
}
