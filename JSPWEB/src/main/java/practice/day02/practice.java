package practice.day02;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class practice
 */
@WebServlet("/practice")
public class practice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public practice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	// Reading
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// ArrayList<Object> list = Dao.getInstance().getData();
    	ArrayList<Dto> list = Dao.getInstance().getData2();
    	

    	// HTML 출력값: [practice.day02.Dto@a2fb108, practice.day02.Dto@32bafb91, practice.day02.Dto@1cb4b7ed]
    	// 해석1: Java 객체와 JS 객체는 체계와 형태가 다르기 때문에, 변환 작업 필요
    	// 해결방법: Json 이용하거나 직접 Java 객체를 JS객체로 변환
    	// Json 이용 시 필요한 자료: 라이브러리 3개 [ jackson-annotations-2.14.2, jackson-core-2.14.2, jackson-databind-2.14.2]
    	
    	// Json Object 생성
    	ObjectMapper objectMapper = new ObjectMapper();
    	String jsonArray = objectMapper.writeValueAsString(list);
    	
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	response.getWriter().print(jsonArray);
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // Create
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
			// ------------------------------------------------ data 송신 확인 완료
			/*
			System.out.println( request.getParameter("info"));
			System.out.println( request.getParameter("data1"));
			System.out.println( request.getParameter("data2"));
			System.out.println( request.getParameter("data3"));
			System.out.println( request.getParameter("data4"));
			System.out.println( request.getParameter("data5"));
			System.out.println( request.getParameter("data6"));
			System.out.println( request.getParameter("data7"));
			System.out.println( request.getParameter("data8"));
			System.out.println( request.getParameter("data9"));
			System.out.println( request.getParameter("data10"));
			*/
		
		// 변수 선언 이유: JS에서 받은 데이터를 변수에 저장
		// 포인트: JS로부터 받은 데이터는 모두 문자열로 들어오기 때문에, 사용하고자 하는 형태에 맞추는 작업이 필요함! (형 변환)
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		double data3 = Double.parseDouble( request.getParameter("data3") );
		int data4 = Integer.parseInt(request.getParameter("data4"));
		String data5 = request.getParameter("data5");
		String data6 = request.getParameter("data6");
		String data7 = request.getParameter("data7");
		boolean data8 = Boolean.parseBoolean(request.getParameter("data8"));
		String data9 = request.getParameter("data9");
		String data10 = request.getParameter("data10");
		
		// Dao에 데이터 전송 후 result 변수로 결과값 반환받기
		Dto dto= new Dto(data1, data2, data3, data4, data5, data6, data7, data8, data9, data10);
		
		boolean result = Dao.getInstance().setDataDto(dto);
		
		// 전달받은 내용 JS에 알려주기: response
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("DB 저장 여부:" + result);
			
	}

}
