package dao.yr;

import java.util.Date;

public class StdApply {

	private int stdnum;
	private String participant_id;
	private Date stdapply_date;
	private Date stdparti_date;
	private String apply_chck;
	private String apply_content;
	public int getStdnum() {
		return stdnum;
	}
	public void setStdnum(int stdnum) {
		this.stdnum = stdnum;
	}
	public String getParticipant_id() {
		return participant_id;
	}
	public void setParticipant_id(String participant_id) {
		this.participant_id = participant_id;
	}
	public Date getStdapply_date() {
		return stdapply_date;
	}
	public void setStdapply_date(Date stdapply_date) {
		this.stdapply_date = stdapply_date;
	}
	public Date getStdparti_date() {
		return stdparti_date;
	}
	public void setStdparti_date(Date stdparti_date) {
		this.stdparti_date = stdparti_date;
	}
	public String getApply_chck() {
		return apply_chck;
	}
	public void setApply_chck(String apply_chck) {
		this.apply_chck = apply_chck;
	}
	public String getApply_content() {
		return apply_content;
	}
	public void setApply_content(String apply_content) {
		this.apply_content = apply_content;
	}
	
	
	
}
