package apply.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apply.model.dao.BoardDao;

@WebServlet("/apply/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public View() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int type = Integer.parseInt(request.getParameter("type"));	// 조회수, 좋아요, 싫어요 타입
		int bNo = Integer.parseInt(request.getParameter("bNo")); 	// 게시물 번호 추적
		
		// (옵션) PC별로 하루에 한번 증가 가능하도록 제어
		// Point> PC 식별 how? IP주소로 식별
		
		// 1. 현재 요청한 클라이언트의 IP 확인: getRemoteAddr() 메소드 이용
		String ip = request.getRemoteAddr();
		//	System.out.println("ip: " + ip);
		// ------------------------------------------------------ 확인 완료
		// 4. 제어 설정 [기존 세션 있으면 증가 못하도록 제어]
		Object o = request.getSession().getAttribute(bNo+type+ip);
		//	System.out.println("o:" + o);
		// ------------------------------------------------------ 확인 완료
		if( o == null ) {
			// 2. 세션 생성
			request.getSession().setAttribute(bNo+type+ip, bNo);
			// 해석1: setAttribute(x,y)메소드 => x:세션정보, y:세션이름 
			// 해석2: bNo+type+ip로 설정한 이유
			// PC 1대 당 한 개의 게시물에 조회수, 좋아요, 싫어요 각 1번씩 가능하게 설정(추적)하기 위함.
			
			// 3. 세션 생명주기 설정
			// 동작 원리: 설정한 생명주기를 초과하면 자동으로 메모리 삭제 처리됨 (단위: 초)
			request.getSession().setMaxInactiveInterval(60*60*24);
			// 해석: 24시간 기준으로 초기화
			// 이 외 초기화 상황: 1) 서버 종료 시, 2) 서버 다시 켰을 경우 
			
			BoardDao.getInstance().bIncrease( type, bNo );
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
