package apply.model.dto;

public class ReplyBoardDto {
	private int rNo;
    private String rContent;
    private String rDate;
    private int rIndex;
    private int mNo;
    private int bNo;
    
    // 추가
    private String mId;
    private String mImg;
    private int rCount;
	
    // Empty 생성자
    public ReplyBoardDto() {
		super();
		// TODO Auto-generated constructor stub
    }
    
    // 등록용 생성자
	public ReplyBoardDto(String rContent, int mNo, int bNo) {
		super();
		this.rContent = rContent;
		this.mNo = mNo;
		this.bNo = bNo;
	}
	
	// Full 생성자
	public ReplyBoardDto(int rNo, String rContent, String rDate, int rIndex, int mNo, int bNo, String mId,
			String mImg) {
		super();
		this.rNo = rNo;
		this.rContent = rContent;
		this.rDate = rDate;
		this.rIndex = rIndex;
		this.mNo = mNo;
		this.bNo = bNo;
		this.mId = mId;
		this.mImg = mImg;
	}
	
	@Override
	public String toString() {
		return "ReplyBoardDto [rNo=" + rNo + ", rContent=" + rContent + ", rDate=" + rDate + ", rIndex=" + rIndex
				+ ", mNo=" + mNo + ", bNo=" + bNo + ", mId=" + mId + ", mImg=" + mImg + "]";
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public int getrIndex() {
		return rIndex;
	}

	public void setrIndex(int rIndex) {
		this.rIndex = rIndex;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmImg() {
		return mImg;
	}

	public void setmImg(String mImg) {
		this.mImg = mImg;
	}

	public int getrCount() {
		return rCount;
	}

	public void setrCount(int rCount) {
		this.rCount = rCount;
	}

}
