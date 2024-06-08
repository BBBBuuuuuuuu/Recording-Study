package membership;

public class LoginVO {

	private String id;
	private int accNum;
	private String gender;

	
	
	public LoginVO() {	}
	

	public LoginVO(String id, int accNum, String gender) {
		this.id = id;
		this.accNum = accNum;
		this.gender = gender;
	}


	public String getId() {
		return id;
	}

	public int getAccNum() {
		return accNum;
	}

	public String getGender() {
		return gender;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
