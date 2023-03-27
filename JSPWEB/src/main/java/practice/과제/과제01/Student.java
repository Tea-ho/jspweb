package practice.과제.과제01;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList<Dto> list = Dao.getInstance().sList();
		
    	ObjectMapper objectMapper = new ObjectMapper();
    	String jsonArray = objectMapper.writeValueAsString(list);
		
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String sName = request.getParameter("sName"); 
		String sPhone = request.getParameter("sPhone");
		double sLength = Double.parseDouble(request.getParameter("sLength"));
		int sAge  = Integer.parseInt(request.getParameter("sAge"));
		String sDate  = request.getParameter("sDate");
		String sSex  = request.getParameter("sSex");
		String sHome  = request.getParameter("sHome");
		String sIntro = request.getParameter("sIntro");
		boolean sAgree = Boolean.parseBoolean(request.getParameter("sAgree"));
		
		Dto dto = new Dto(0, sName, sPhone, sLength, sAge, sDate, sSex, sHome, sIntro, sAgree);
		
		boolean result = Dao.getInstance().submit(dto);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("DB 저장 여부:" + result);
	}

}
