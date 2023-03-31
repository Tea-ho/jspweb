package apply.controller.member;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import apply.model.dao.MemberDao;
import apply.model.dao.ProductDao;

/**
 * Servlet implementation class Point
 */
@WebServlet("/apply/point")
public class Point extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Point() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Integer> result = ProductDao.getInstance().getSum();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString( result );
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	// point 결제 기능
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mpcomment = request.getParameter("mpcomment");
		int mpamount = Integer.parseInt(request.getParameter("mpamount"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		boolean result = MemberDao.getInstance().setPoint( mpcomment, mpamount, mno );
		response.getWriter().print(result);
		
	}

}
