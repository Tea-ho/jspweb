package apply.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import apply.model.dao.MemberDao;
import apply.model.dto.MemberDto;

@WebServlet("/apply/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = (String)request.getSession().getAttribute("login");
		
		MemberDto result = MemberDao.getInstance().getMember( mid );
			System.out.println( result );
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result);
			System.out.println( json );
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		boolean result = MemberDao.getInstance().login(mid,mpw);
		
			System.out.println(result);
		
		// 서버[톰캣] 내 세션 객체 호출하기
		if( result ) { request.getSession().setAttribute("login", mid); }
		response.getWriter().print(result);
	}
}
