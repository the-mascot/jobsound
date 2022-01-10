package dao.sw;

import java.util.Date;

public class Board {
	
	private String b_type;
	private int b_num;
	private String id;
	private Date uploaddate;
	private String content;
	private String title;
	private String imagepath;
	private int views;
	private String commcontent;
	private Date commdate;
	private int re_level;
	private int re_step;
	private int ref;
	private int stdnum;
	
	// Getter & Setter
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
	public Date getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
	public int getStdnum() {
		return stdnum;
	}
	public void setStdnum(int stdnum) {
		this.stdnum = stdnum;
	}
	
}
