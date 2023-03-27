package apply.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import apply.model.dao.BoardDao;
import apply.model.dao.MemberDao;
import apply.model.dto.ReplyBoardDto;


@WebServlet("/apply/board/Reply")
public class Reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Reply() {
        super();
    }
    
    // 댓글 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 필요 데이터 가져오기
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int type = Integer.parseInt(request.getParameter("type"));
		
		int rIndex = 0; // 상위(부모) 댓글 rIndex값으로 기본값 설정
		if( type == 1 ) { // type1: 상위(부모) 댓글
			
		}
		else if( type == 2 ) { // type2: s하위(자식) 댓글
			rIndex = Integer.parseInt(request.getParameter("rIndex"));	
		}
		
		// 2. Dao 결과값 가져오기
		ArrayList<ReplyBoardDto> result =  BoardDao.getInstance().getReplyList(bNo, rIndex);
		
		// 3. Java 객체 -> JS 객체로 변환 (json 이용)
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		
		// 4. JS 객체 JS 파일에 전송
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}
	
	// 댓글 입력
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2. 필요 데이터 가져오기
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int mNo = MemberDao.getInstance().getMNo((String)request.getSession().getAttribute("login"));
		
		String rContent = request.getParameter("rContent"); // JS에서 rContent 변수명 동일하게 사용하여 공용 사용
		int type = Integer.parseInt(request.getParameter("type"));
		
		// 3. Dto, Dao 활용하여 데이터 전달
		ReplyBoardDto dto = new ReplyBoardDto(rContent, mNo, bNo);
		// 해석: dto 공용 사용!
		
		if( type == 1 ) { // type1: 상위 댓글 (스타트 댓글 / 부모 댓글)
			System.out.println("ReplyBoardDto" + dto);
		}
		else if( type == 2 ) { // type2: 하위 댓글 (스타트 댓글 밑 추가 댓글 / 자식 댓글)
			int rIndex = Integer.parseInt(request.getParameter("rIndex"));
			dto.setrIndex(rIndex);
			// 해석: 자식 댓글의 경우, dto에 rIndex 데이터만 추가 입력
		}
		boolean result = BoardDao.getInstance().rwrite(dto);
		// 4. 결과 전달
		response.getWriter().print(result);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
