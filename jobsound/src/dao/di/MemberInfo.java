package dao.di;

import java.util.Date;

public class MemberInfo {
	private String id;
	private String name;
	private String nickname;
	private String passwd;
	private String gender;
	private String tel;
	private String email;
	private String birth;
	private String addr;
	private String reg_chk;
	private String widraw;
	private Date regDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getReg_chk() {
		return reg_chk;
	}
	public void setReg_chk(String reg_chk) {
		this.reg_chk = reg_chk;
	}
	public String getWidraw() {
		return widraw;
	}
	public void setWidraw(String widraw) {
		this.widraw = widraw;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
