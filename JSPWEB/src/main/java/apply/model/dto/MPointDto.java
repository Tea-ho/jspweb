package apply.model.dto;

public class MPointDto {
	private int mpno;
	private String mpcomment;
	private int mpamount;
	private String mpdate;
	private int mno;
	public MPointDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MPointDto(int mpno, String mpcomment, int mpamount, int mno) {
		super();
		this.mpno = mpno;
		this.mpcomment = mpcomment;
		this.mpamount = mpamount;
		this.mno = mno;
	}
	
	public MPointDto(int mpno, String mpcomment, int mpamount, String mpdate, int mno) {
		super();
		this.mpno = mpno;
		this.mpcomment = mpcomment;
		this.mpamount = mpamount;
		this.mpdate = mpdate;
		this.mno = mno;
	}
	public int getMpno() {
		return mpno;
	}
	public void setMpno(int mpno) {
		this.mpno = mpno;
	}
	public String getMpcomment() {
		return mpcomment;
	}
	public void setMpcomment(String mpcomment) {
		this.mpcomment = mpcomment;
	}
	public int getMpamount() {
		return mpamount;
	}
	public void setMpamount(int mpamount) {
		this.mpamount = mpamount;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMpdate() {
		return mpdate;
	}
	public void setMpdate(String mpdate) {
		this.mpdate = mpdate;
	}
	@Override
	public String toString() {
		return "MPointDto [mpno=" + mpno + ", mpcomment=" + mpcomment + ", mpamount=" + mpamount + ", mno=" + mno + "]";
	}
}
