package apply.model.dto;

import java.util.List;

public class ProductDto {
	
	// 기본 DB 컬럼 필드
	private int pNo;
	private String pName;
	private String pComment;
	private int pPrice;
	private int pState;
	private String pLat;
	private String pLng;
	private int pView;
	private String pDate;
	
	// 추가 필드: 등록한 회원번호, 회원아이디, 사진목록
	private int mNo;
	private String mId;
	private List<String> pimgList;
	
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	// 등록용 생성자: 제품명, 제품설명, 제품가격, 위도, 경도, 등록회원번호, 등록한 사진목록
	public ProductDto( String pName, String pComment, int pPrice, String pLat, String pLng, int mNo, List<String> pimgList ) {
		this.pName = pName;
		this.pComment = pComment;
		this.pPrice = pPrice;
		this.pLat = pLat;
		this.pLng = pLng;
		this.mNo = mNo;
		this.pimgList = pimgList;
	}


	public ProductDto(int pNo, String pName, String pComment, int pPrice, int pState, String pLat, String pLng,
			int pView, String pDate, int mNo, String mId, List<String> pimgList) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.pComment = pComment;
		this.pPrice = pPrice;
		this.pState = pState;
		this.pLat = pLat;
		this.pLng = pLng;
		this.pView = pView;
		this.pDate = pDate;
		this.mNo = mNo;
		this.mId = mId;
		this.pimgList = pimgList;
	}

	@Override
	public String toString() {
		return "ProductDto [pNo=" + pNo + ", pName=" + pName + ", pComment=" + pComment + ", pPrice=" + pPrice
				+ ", pState=" + pState + ", pLat=" + pLat + ", pLng=" + pLng + ", pView=" + pView + ", pDate=" + pDate
				+ ", mNo=" + mNo + ", mId=" + mId + ", pimgList=" + pimgList + "]";
	}
	
	public int getpNo() {
		return pNo;
	}


	public void setpNo(int pNo) {
		this.pNo = pNo;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public String getpComment() {
		return pComment;
	}


	public void setpComment(String pComment) {
		this.pComment = pComment;
	}


	public int getpPrice() {
		return pPrice;
	}


	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}


	public int getpState() {
		return pState;
	}


	public void setpState(int pState) {
		this.pState = pState;
	}


	public String getpLat() {
		return pLat;
	}


	public void setpLat(String pLat) {
		this.pLat = pLat;
	}


	public String getpLng() {
		return pLng;
	}


	public void setpLng(String pLng) {
		this.pLng = pLng;
	}


	public int getpView() {
		return pView;
	}


	public void setpView(int pView) {
		this.pView = pView;
	}


	public String getpDate() {
		return pDate;
	}


	public void setpDate(String pDate) {
		this.pDate = pDate;
	}


	public int getmNo() {
		return mNo;
	}


	public void setmNo(int mNo) {
		this.mNo = mNo;
	}


	public String getmId() {
		return mId;
	}


	public void setmId(String mId) {
		this.mId = mId;
	}


	public List<String> getPimgList() {
		return pimgList;
	}


	public void setPimgList(List<String> pimgList) {
		this.pimgList = pimgList;
	}
}
