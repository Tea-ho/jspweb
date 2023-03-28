package apply.model.dto;

public class ProductchatDto {
	
	// DB 필드
	// 기능: 댓글 형식의 쪽지 주고받기
	// DB설계: note number(PK), 내용, 일자, 제품번호, 보낸사람번호, 받는사람번호
	// 원리: 해당 제품을 등록한 사람에게 로그인한 회원이 쪽지를 보낸다.
	// 활용: 
	private int nno;
    private String ncontent;
    private String ndate;
    private int pno;
    private int frommno;
    private int tomno;
    
    // 추가 필드
    
	public ProductchatDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public ProductchatDto(int nno, String ncontent, String ndate, int pno, int frommno, int tomno) {
		super();
		this.nno = nno;
		this.ncontent = ncontent;
		this.ndate = ndate;
		this.pno = pno;
		this.frommno = frommno;
		this.tomno = tomno;
	}

	@Override
	public String toString() {
		return "productchatDto [nno=" + nno + ", ncontent=" + ncontent + ", ndate=" + ndate + ", pno=" + pno
				+ ", frommno=" + frommno + ", tomno=" + tomno + "]";
	}

	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getFrommno() {
		return frommno;
	}
	public void setFrommno(int frommno) {
		this.frommno = frommno;
	}
	public int getTomno() {
		return tomno;
	}
	public void setTomno(int tomno) {
		this.tomno = tomno;
	}
}
