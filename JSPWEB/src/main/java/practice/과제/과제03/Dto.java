package practice.과제.과제03;

public class Dto {
	
	int pNo;
    String pTitle;
    int pPrice;
    String pContent;
    String bdate;
    
	public Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dto(int pNo, String pTitle, int pPrice, String pContent, String bdate) {
		super();
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.pPrice = pPrice;
		this.pContent = pContent;
		this.bdate = bdate;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}	
}
