package dao.sw;

import java.util.Date;

public class Inquire {
	
	private int inqnum;
	private String id;
	private String inqtitle;
	private String inqcontent;
	private String askcontent;
	private Date writedate;
	private Date askdate;
	
	// Getter & Setter
	public int getInqnum() {
		return inqnum;
	}
	public void setInqnum(int inqnum) {
		this.inqnum = inqnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInqtitle() {
		return inqtitle;
	}
	public void setInqtitle(String inqtitle) {
		this.inqtitle = inqtitle;
	}
	public String getInqcontent() {
		return inqcontent;
	}
	public void setInqcontent(String inqcontent) {
		this.inqcontent = inqcontent;
	}
	public String getAskcontent() {
		return askcontent;
	}
	public void setAskcontent(String askcontent) {
		this.askcontent = askcontent;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public Date getAskdate() {
		return askdate;
	}
	public void setAskdate(Date askdate) {
		this.askdate = askdate;
	}
	
}