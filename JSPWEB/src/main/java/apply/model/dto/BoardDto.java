package apply.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDto {

	private int bNo;
	private String bTitle;
	private String bContent;
	private String bFile;
	private String bDate;
	private int bView;
	private int bUp;
	private int bDown;
	private int mNo;
	private int cNo;
	
	private String mId;
	private String mImg;
	private int rCount;
   
	// 1. Empty 생성자
	public BoardDto() {	}
	
	// 2. Full 생성자
	public BoardDto(int bNo, String bTitle, String bContent, String bFile, String bDate, int bView, int bUp, int bDown,
			int mNo, int cNo) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bFile = bFile;
		this.bDate = bDate;
		this.bView = bView;
		this.bUp = bUp;
		this.bDown = bDown;
		this.mNo = mNo;
		this.cNo = cNo;
	}

	// 3. 등록용 생성자 
	public BoardDto(String bTitle, String bContent, String bFile, int mNo, int cNo) {
		super();
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bFile = bFile;
		this.mNo = mNo;
		this.cNo = cNo;
	}
	// 4. 출력용 생성자 
	public BoardDto(int bNo, String bTitle, String bContent, String bFile, String bDate, int bView, int bUp, int bDown,
			int mNo, int cNo, String mId, String mImg) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bFile = bFile;
		
		// 시간 표기 형식
		// type1. 오늘 날짜와 작성일 동일하면 시간 표기
		// type2. 아닌 경우, 날짜만 표기
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
		String now = sdf.format(date);
		
		if( now.split(" ")[0].equals( bDate.split(" ")[0]) ) { // type1
			this.bDate = bDate.split(" ")[1];
		} else { // type2
			this.bDate = bDate.split(" ")[0];
		}	
		
		this.bView = bView;
		this.bUp = bUp;
		this.bDown = bDown;
		this.mNo = mNo;
		this.cNo = cNo;
		this.mId = mId;
		this.mImg = mImg;
	}
	
	// 5. 업데이트용 생성자
	public BoardDto(int bNo, String bTitle, String bContent, String bFile, int cNo) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bFile = bFile;
		this.cNo = cNo;
	}

	@Override
	public String toString() {
		return "BoardDto [bNo=" + bNo + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bFile=" + bFile
				+ ", bDate=" + bDate + ", bView=" + bView + ", bUp=" + bUp + ", bDown=" + bDown + ", mNo=" + mNo
				+ ", cNo=" + cNo + "]";
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbFile() {
		return bFile;
	}

	public void setbFile(String bFile) {
		this.bFile = bFile;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public int getbView() {
		return bView;
	}

	public void setbView(int bView) {
		this.bView = bView;
	}

	public int getbUp() {
		return bUp;
	}

	public void setbUp(int bUp) {
		this.bUp = bUp;
	}

	public int getbDown() {
		return bDown;
	}

	public void setbDown(int bDown) {
		this.bDown = bDown;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
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
