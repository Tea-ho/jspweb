package apply.model.dto;

import java.util.ArrayList;

public class PageDto {
	
	private int page; 		// 현재 페이지
	private int listSize; 	// 한 페이지에 출력되는 게시물 수
	private int startrow; 	// 해당 페이지에서 출력되는 게시물의 시작번호
	private int totalSize; 	// 총 게시물 수
	private int totalPage; 	// 총 페이지 수
	private int btnSize;	// 페이징 버튼 최대 출력 수
	private int startBtn;	// 페이징 시작 버튼
	private int endBtn; 	// 페이징 마지막 버튼
	
	
	ArrayList<BoardDto> boardList;
	ArrayList<MemberDto> memberList;
	private String type;	// 회원 리스트 표시
	
	public PageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 게시글
	public PageDto(int page, int listSize, int startrow, int totalSize, int totalPage, int btnSize, int startBtn,
			int endBtn, ArrayList<BoardDto> boardList) {
		super();
		this.page = page;
		this.listSize = listSize;
		this.startrow = startrow;
		this.totalSize = totalSize;
		this.totalPage = totalPage;
		this.btnSize = btnSize;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.boardList = boardList;
	}
	
	// 회원
	public PageDto(int page, int listSize, int startrow, int totalSize, int totalPage, int btnSize, int startBtn,
			int endBtn, ArrayList<MemberDto> memberList, String type) {
		this.page = page;
		this.listSize = listSize;
		this.startrow = startrow;
		this.totalSize = totalSize;
		this.totalPage = totalPage;
		this.btnSize = btnSize;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.memberList = memberList;
		this.type = type;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBtnSize() {
		return btnSize;
	}

	public void setBtnSize(int btnSize) {
		this.btnSize = btnSize;
	}

	public int getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(int startBtn) {
		this.startBtn = startBtn;
	}

	public int getEndBtn() {
		return endBtn;
	}

	public void setEndBtn(int endBtn) {
		this.endBtn = endBtn;
	}

	public ArrayList<BoardDto> getBoardList() {
		return boardList;
	}

	public void setBoardList(ArrayList<BoardDto> boardList) {
		this.boardList = boardList;
	}

	public ArrayList<MemberDto> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<MemberDto> memberList) {
		this.memberList = memberList;
	}

}
