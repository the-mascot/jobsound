package dao.sh;

import java.util.Date;

public class Comment {
	private String b_type;
	private int b_num;
	private String id;
	private String commnum;
	private String commcontent;
	private Date commdate;
	private int re_level;
	private int re_step;
	private int ref;
	
	
	public String getB_type() {
		return b_type;
	}
	public void setB_type(String b_type) {
		this.b_type = b_type;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommnum() {
		return commnum;
	}
	public void setCommnum(String commnum) {
		this.commnum = commnum;
	}
	public String getCommcontent() {
		return commcontent;
	}
	public void setCommcontent(String commcontent) {
		this.commcontent = commcontent;
	}
	public Date getCommdate() {
		return commdate;
	}
	public void setCommdate(Date commdate) {
		this.commdate = commdate;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	
	
}
