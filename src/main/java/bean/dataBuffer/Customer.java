package bean.dataBuffer;
 
public class Customer {
	private String s_id;
 	private String password;
 	private String password2;
    private  String s_name;
 	private String  nickname;
 	private String   sex;
 	private String email;
	private String c_id;
 
 
	public String getC_id() {
	return c_id;
}
	public void setC_id(String c_id) {
	this.c_id = c_id;
}
	public String getS_id() {
	return s_id;
}
	public void setS_id(String s_id) {
	this.s_id = s_id;
}
	public String getPassword() {
	return password;
}
	public void setPassword(String password) {
	this.password = password;
}
	public String getS_name() {
	return s_name;
}
	public void setS_name(String s_name) {
	this.s_name = s_name;
}
	public String getNickname() {
	return nickname;
}

	public String getPassword2() {
		return password2;
	}

	public void setNickname(String nickname) {
	this.nickname = nickname;
}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getSex() {
	return sex;
}
	public void setSex(String sex) {
	this.sex = sex;
}
	public String getEmail() {
	return email;
}
	public void setEmail(String email) {
	this.email = email;
}


 
}