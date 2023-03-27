package practice.과제.과제03;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/과제03")
public class 과제03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public 과제03() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<Dto> result = Dao.getInstance().onList();
		
		// Java 객체 -> JSON[JS객체] 형식으로 변환
		ObjectMapper mapper = new ObjectMapper();
		// 해석: 형식 변환을 위해 mapper 객체 생성 (JACKSON 라이브러리에서 제공하는 클래스)
		String jsonArray = mapper.writeValueAsString(result);
		
		response.setContentType("appliaction/json");
		response.getWriter().print(jsonArray);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pTitle = request.getParameter( "pTitle" );
		int pPrice = Integer.parseInt( request.getParameter( "pPrice" ));
		String pContent = request.getParameter("pContent");
		
		Dto dto = new Dto(0, pTitle, pPrice, pContent, null);
		boolean result = Dao.getInstance().onAdd(dto);
		
		response.getWriter().print(result);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int pNo = Integer.parseInt( request.getParameter( "pNo" ));
		String pTitle = request.getParameter( "newTitle" );
		int pPrice = Integer.parseInt( request.getParameter( "newPrice" ));
		String pContent = request.getParameter("newContent");
		
		boolean result = Dao.getInstance().onUpdate(pNo, pTitle, pPrice, pContent);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int pNo = Integer.parseInt( request.getParameter( "pNo" ));
		
		boolean result = Dao.getInstance().onDelete(pNo);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	}

}
