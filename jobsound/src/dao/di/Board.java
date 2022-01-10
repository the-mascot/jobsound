package dao.di;

import java.util.Date;

public class Board {
	private String b_type;
	private int b_num;
	private String id;
	private Date uploadDate;
	private String content;
	private String title;
	private String imagePath;
	private int views;
	private String commNum;
	private String commContent;
	private Date commDate;
	private int re_level;
	private int re_step;
	private int ref;
	private int stdNum;
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
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getCommNum() {
		return commNum;
	}
	public void setCommNum(String commNum) {
		this.commNum = commNum;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	public Date getCommDate() {
		return commDate;
	}
	public void setCommDate(Date commDate) {
		this.commDate = commDate;
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
	public int getStdNum() {
		return stdNum;
	}
	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}
	@Override
	public String toString() {
		return "Board [b_type=" + b_type + ", b_num=" + b_num + ", id=" + id + ", uploadDate=" + uploadDate
				+ ", content=" + content + ", title=" + title + ", imagePath=" + imagePath + ", views=" + views
				+ ", commNum=" + commNum + ", commContent=" + commContent + ", commDate=" + commDate + ", re_level="
				+ re_level + ", re_step=" + re_step + ", ref=" + ref + ", stdNum=" + stdNum + "]";
	}
	
	
}
