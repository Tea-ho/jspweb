package apply.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import apply.model.dao.BoardDao;
import apply.model.dao.MemberDao;
import apply.model.dto.BoardDto;
import apply.model.dto.PageDto;


@WebServlet("/apply/Write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Write() {
        super();
    }

    // 게시글 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = Integer.parseInt(request.getParameter("type"));
		if( type == 1 ) { // 전체 출력
			
			// ------------------------ 카테고리 별 출력 -------------------------
			// 1. 카테고리 No 가져오기 => getTotalSize, getBoardList에 매개변수 전달
			int cNo = Integer.parseInt(request.getParameter("cNo"));
			
			// ------------------------ 검색 처리 -------------------------
			// 1. 검색에 필요한 매개변수 가져오기 => getTotalSize, getBoardList에 매개변수 전달 (SQL where문에 사용하기 위함)
			String key = request.getParameter("key");
			String keyword = request.getParameter("keyword");
			
			// 추가 기능: 페이징 처리
			// ------------------------ 페이지 처리 -------------------------
			// 1. 현재페이지[요청], 2. 현재페이지[게시물시작, 게시물끝]
			int page = Integer.parseInt(request.getParameter("page"));
			int listsize = Integer.parseInt( request.getParameter("listsize") );
			// 목적: 한 페이지에 출력되는 게시물 수 설정
			int startrow = (page-1)*listsize;
			// 목적: 해당 페이지에서 출력되는 게시물의 시작번호 설정
			// 해석: 1)1페이지 (1-1)*3 = 0, 2)2페이지 (2-1)*3 = 3, 3)3페이지 (3-1)*3 = 6
			// 활용: SQL limit 조건으로 이용 (limit 시작 인덱스, 선택수량)
			
			// ------------------------ 페이지 버튼 -------------------------
			// 1. 전체페이지수 2. 페이지 표시할 최대 버튼수 3. 시작버튼 번호
			int totalSize = BoardDao.getInstance().getTotalSize( key, keyword, cNo );
			int totalPage = totalSize % listsize == 0 ? (totalSize/listsize) : ((totalSize/listsize)+1);
			// 해석1: 페이지 수 = 전체 게시글 수 / 한 페이지에 출력되는 개시물 수  
			// 해석2: 나머지 0이면 그대로 사용, 나머지 1이상이면 나눈 값 +1
			
			int btnSize = 10;
			// 목적: 버튼 출력되는 사이즈 설정 (10개 출력)
			int startBtn = ((page-1)/btnSize)*btnSize + 1;
			int endBtn = startBtn + ( btnSize - 1);
			if( endBtn >= totalPage ) { endBtn = totalPage; }
			
			
			ArrayList<BoardDto> result = BoardDao.getInstance().getBoardList( startrow, listsize, key, keyword, cNo );
			
			// page Dto 생성
			PageDto pageDto = new PageDto(page, listsize, startrow, totalSize, totalPage, btnSize, startBtn, endBtn, result);
			
			// 2. 형태 변환 (JAVA 객체 -> JS객체)
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString(pageDto);
		
			// 3. 전송
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		
		else if( type == 2 ) { // 개별 출력
			int bno = Integer.parseInt(request.getParameter("bNo"));
			// 1. Dao 결과 가져오기
			BoardDto result = BoardDao.getInstance().getBoard(bno);
			
			// 2. 형태 변환 (JAVA 객체 -> JS객체)
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(result);
			
			// 3. 전송
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(json);
		}
	}

	// 게시글 작성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HTML Form 형식 연결 작업
		String uploadpath = request.getSession().getServletContext().getRealPath("/apply/board/bfile");
			System.out.println( "uploadpath:" + uploadpath);
		
		MultipartRequest multi = new MultipartRequest(
									request,						// 요청방식 
									uploadpath,						// 첨부파일 저장할 서버 폴더
									1024*1024*10,					// 첨부파일 용량 설정 [단위 byte로 설정되어 있음]
									"UTF-8",						// 인코딩 타입 설정
									new DefaultFileRenamePolicy()	// 동일한 첨부파일명으로 중복 업로드 될 경우, 식별이 불가능해지는 상황 예방하는 옵션
																	// 작동원리: 동일 첨부명 존재하면 뒤에 숫자 자동 부여
								);
		// System.out.println("multi:" + multi);
		// ----------------------------------------------------------- 확인 완료
		
		int cNo = Integer.parseInt(multi.getParameter("cNo"));
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");		// getParameter: 값을 가져오는 메소드
		String bFile = multi.getFilesystemName("bFile");		// getFilesystemName: 파일명 가져오는 메소드
		
		// System.out.println(cNo);			System.out.println(bTitle);
		// System.out.println(bContent);	System.out.println(bFile);
		
		// ----------------------------------------------------------- 확인 완료
		
		// mNo: 로그인된 회원 정보
		// 1. mNo 추적을 위해 mId 정보 가져오기 
		String mId = (String)request.getSession().getAttribute("login");
		//	System.out.println("mId:" + mId);
		// ----------------------------------------------------------- 확인 완료
		
		// 2. mId -> mNo
		int mNo = MemberDao.getInstance().getMNo(mId);
		
		 if( mNo < 1 ) { response.getWriter().print("false"); }
		
		BoardDto dto = new BoardDto(bTitle, bContent, bFile, mNo, cNo);
		//	System.out.println( "dto:" + dto );
		// ----------------------------------------------------------- 확인 완료
		boolean result = BoardDao.getInstance().bWrite(dto);
		response.getWriter().print(result);
	}

	// 게시글 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String path = request.getSession().getServletContext().getRealPath("/apply/board/bfile");
		
		MultipartRequest multi = new MultipartRequest(
				request,						// 요청방식 
				path,							// 첨부파일 저장할 서버 폴더
				1024*1024*10,					// 첨부파일 용량 설정 [단위 byte로 설정되어 있음]
				"UTF-8",						// 인코딩 타입 설정
				new DefaultFileRenamePolicy()	// 동일한 첨부파일명으로 중복 업로드 될 경우, 식별이 불가능해지는 상황 예방하는 옵션
												// 작동원리: 동일 첨부명 존재하면 뒤에 숫자 자동 부여
			);
		
		// 수정 리스트
		int bNo = Integer.parseInt( multi.getParameter("bNo") );
		int cNo = Integer.parseInt( multi.getParameter("cNo") );
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
		String bFile = multi.getParameter("bFile");
		
		// 첨부파일 수정 경우의 수
		// 1) 기존 첨부파일 없을 경우
			// 신규 첨부파일 X : [X]
			// 신규 첨부파일 O : [신규 파일로 업로드]
		// 2) 기존 첨부파일 있을 경우
			// 신규 첨부파일 X : [기존 첨부파일 유지 시, 기존파일명으로 업로드 처리], [기존 첨부파일 삭제 시, 삭제처리]
			// 신규 첨부파일 O : [기존파일 삭제 후 신규파일로 업데이트 처리]
		
		// 1. 기존 첨부파일명 가져오기
		String oldFile = BoardDao.getInstance().getBoard(bNo).getbFile();
		
		if( bFile == null ) { // 기존 첨부파일 X
			bFile = oldFile;
		} else { // 기존 첨부파일 O
			String oldpath = request.getSession().getServletContext().getRealPath("/apply/board/bfile"+oldFile);
			File file = new File(oldpath); 	if( file.exists() ) { file.delete(); }
		}
		
		
		BoardDto updatedto = new BoardDto(bNo, bTitle, bContent, bFile, cNo);
			System.out.println("updatedto: " + updatedto );
		
		boolean result = BoardDao.getInstance().bupdate( updatedto );
			response.getWriter().print(result);
		
	}

	// 게시글 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = Integer.parseInt( request.getParameter("type") );
		int bNo = Integer.parseInt( request.getParameter("bNo") );
			// System.out.println("bNo확인" + bNo);
				
		// 1. 삭제 전 기존 게시물 첨부파일 이름 확인
		String bFile = BoardDao.getInstance().getBoard(bNo).getbFile();
		
		boolean result = true; // 파일 존재하는 전제 조건
		
		if( type == 1 ) { // DB 삭제 + 파일 삭제
			result = BoardDao.getInstance().bdelete(bNo);
		}
		else if( type == 2 ) { // DB 업데이트 + 파일 삭제
			result = BoardDao.getInstance().bfileDelete(bNo);
			// 2. 경로 찾기
			String path = request.getSession().getServletContext().getRealPath("/apply/board/bfile/"+bFile);
			// 3. 파일 객체화
			File file = new File(path);
			if( file.exists() ) {	// 파일 존재 여부 확인
				file.delete();		// 파일 삭제
			}
		}
		
		// 공통 적용 사항: 파일 삭제
		if( result ) {
			// 2. 경로 찾기
			String path = request.getSession().getServletContext().getRealPath("/apply/board/bfile/"+bFile);
			// 3. 파일 객체화
			File file = new File(path);
			if( file.exists() ) {	// 파일 존재 여부 확인
				file.delete();		// 파일 삭제
			}
		}
		response.getWriter().print(result);
	}
}
