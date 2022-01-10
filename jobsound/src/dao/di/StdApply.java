package dao.di;

import java.sql.Date;

public class StdApply {
	private int stdNum;
	private String participant_id;
	private Date stdApply_Date;
	private Date stdParti_date;
	private String apply_chk;
	private String apply_Content;
	public int getStdNum() {
		return stdNum;
	}
	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}
	public String getParticipant_id() {
		return participant_id;
	}
	public void setParticipant_id(String participant_id) {
		this.participant_id = participant_id;
	}
	public Date getStdApply_Date() {
		return stdApply_Date;
	}
	public void setStdApply_Date(Date stdApply_Date) {
		this.stdApply_Date = stdApply_Date;
	}
	public Date getStdParti_date() {
		return stdParti_date;
	}
	public void setStdParti_date(Date stdParti_date) {
		this.stdParti_date = stdParti_date;
	}
	public String getApply_chk() {
		return apply_chk;
	}
	public void setApply_chk(String apply_chk) {
		this.apply_chk = apply_chk;
	}
	public String getApply_Content() {
		return apply_Content;
	}
	public void setApply_Content(String apply_Content) {
		this.apply_Content = apply_Content;
	}
	
}
