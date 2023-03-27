package practice.day03;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/Ex03/Board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Board() { }

    // R: 읽기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답 매개변수 한글로 인코딩
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<BDto> result = BDao.getInstance().onlist();
		
		// Java 객체 -> JSON[JS객체] 형식으로 변환
		ObjectMapper mapper = new ObjectMapper();
		// 해석: 형식 변환을 위해 mapper 객체 생성 (JACKSON 라이브러리에서 제공하는 클래스)
		String jsonArray = mapper.writeValueAsString(result);
		// 해석: 
		
		response.setContentType("appliaction/json");
		response.getWriter().print(jsonArray);
		
	}
	
	// C: 쓰기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청시 한글 인코딩 
		request.setCharacterEncoding("UTF-8");
		// 2. 매개변수 요청 [ AJAX data속성에서 보내준 매개변수 이름 
		String content = request.getParameter("content");				System.out.println( " content : " + content );
		String writer = request.getParameter("writer");					System.out.println( " writer : " + writer );
		// 3. Dto 객체 [ 기본값 : int 필드의 0 / 객체 필드의 null ]
		BDto boardDto = new BDto( 0 , content, writer, null);	System.out.println( " dto : " + boardDto );
		// 4. Dao 호출해서 결과 저장
		boolean result = BDao.getInstance().onWrite(boardDto);		System.out.println( " onwrite result : " + result  );
		// 5. Dao 결과인 true , false 데이터를 response 객체 이용한 응답
		// 'true' vs '{ true }'
		response.getWriter().print(result);
	
	}

	// U: 업데이트
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String newContent = request.getParameter("newContent");
		
		boolean result = BDao.getInstance().onUpdate(bno, newContent);
		
		response.getWriter().print(result);

		
	}
	
	// D: 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		boolean result = BDao.getInstance().onDelete(bno);
		
		response.getWriter().print(result);
		
	}

	/*
		JSP 서블릿에서 HTTP 메소드 put, delete 사용시 설정사항
		1. Project Explorer - Servers - Tomcat v9.0 - server.xml
		2. 아래 문구에 내용 추가
		변경전: <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
		변경후: <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" parseBodyMethods="get,post,put,delete" URIEncoding="UTF-8" />
/>	
	*/
	
	
}
