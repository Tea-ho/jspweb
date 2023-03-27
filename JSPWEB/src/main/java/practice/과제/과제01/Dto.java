package practice.과제.과제01;

public class Dto {
	
	int sNo;
	String sName;
	String sPhone;
	double sLength;
	int sAge;
	String sDate;
	String sSex;
	String sHome;
	String sIntro;
	boolean sAgree;
	
	public Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Dto(int sNo, String sName, String sPhone, double sLength, int sAge, String sDate, String sSex, String sHome,
			String sIntro, boolean sAgree) {
		this.sNo = sNo;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sLength = sLength;
		this.sAge = sAge;
		this.sDate = sDate;
		this.sSex = sSex;
		this.sHome = sHome;
		this.sIntro = sIntro;
		this.sAgree = sAgree;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	public double getsLength() {
		return sLength;
	}
	public void setsLength(double sLength) {
		this.sLength = sLength;
	}
	public int getsAge() {
		return sAge;
	}
	public void setsAge(int sAge) {
		this.sAge = sAge;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String getsSex() {
		return sSex;
	}
	public void setsSex(String sSex) {
		this.sSex = sSex;
	}
	public String getsHome() {
		return sHome;
	}
	public void setsHome(String sHome) {
		this.sHome = sHome;
	}
	public String getsIntro() {
		return sIntro;
	}
	public void setsIntro(String sIntro) {
		this.sIntro = sIntro;
	}
	public boolean issAgree() {
		return sAgree;
	}
	public void setsAgree(boolean sAgree) {
		this.sAgree = sAgree;
	}
}
