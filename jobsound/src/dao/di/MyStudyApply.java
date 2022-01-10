package dao.di;

import java.util.Date;

public class MyStudyApply {
	private int stdNum;
	private String stdTitle;
	private Date recru_date;
	private Date recu_date;
	private String stdStatus;
	private String apply_chk;
	public int getStdNum() {
		return stdNum;
	}
	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}
	public String getStdTitle() {
		return stdTitle;
	}
	public void setStdTitle(String stdTitle) {
		this.stdTitle = stdTitle;
	}
	public Date getRecru_date() {
		return recru_date;
	}
	public void setRecru_date(Date recru_date) {
		this.recru_date = recru_date;
	}
	public Date getRecu_date() {
		return recu_date;
	}
	public void setRecu_date(Date recu_date) {
		this.recu_date = recu_date;
	}
	public String getStdStatus() {
		return stdStatus;
	}
	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}
	public String getApply_chk() {
		return apply_chk;
	}
	public void setApply_chk(String apply_chk) {
		this.apply_chk = apply_chk;
	}
	
}
