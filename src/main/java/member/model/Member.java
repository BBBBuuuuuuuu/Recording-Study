package member.model;

import java.util.Date;

public class Member {

	private String id;
	private String name;
	private String password;
	private Date regDate;
	private int phone;
	private int accNum;
	private String gender;

	
	public Member() {	}

	public Member(String id, String name, String password, Date regDate, int phone, int accNum, String gender) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
		this.phone = phone;
		this.accNum = accNum;
		this.gender = gender;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}

}
