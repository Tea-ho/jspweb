package practice.day01.index;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practice.day01.model.*;

/**
 * Servlet implementation class IndexTest
 */
@WebServlet("/IndexTest")
public class IndexTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 한글 인코딩 세팅
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<String> result = Dao.getInstance().getData();
		System.out.println("확인: " + result);
		
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 한글 인코딩 세팅
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 1. HTTP 객체 [ request: JS에게 요청, response: JS에게 응답]
			// 1) request 사용: request.getParameter("매개변수명");
			// 매개변수명: JS에서 가져올 $.ajax의 data 매개변수명
		String data = request.getParameter("data");
		System.out.println("JS post 방식으로 받은 데이터: " + data );
		
		// 2. DB 연동
		boolean result = Dao.getInstance().setData(data);
		response.getWriter().print("post 방식으로 success" + result);
		
	}
	
}
