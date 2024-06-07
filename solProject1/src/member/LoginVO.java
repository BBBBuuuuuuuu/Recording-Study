package member;

public class LoginVO {

	private String id;
	private String pw;
	private String name;
	private String phone;

	public LoginVO() {}
	
	public LoginVO(String id, String pw, String name, String phone) {
		setId(id);
		setPw(pw);
		setName(name);
		setPhone(phone);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
		return "이름 : " + name + " | ID : " + id + ", | PW : " + pw ;
	}
}
