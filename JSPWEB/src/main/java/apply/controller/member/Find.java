package apply.controller.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apply.model.dao.MemberDao;

@WebServlet("/apply/Find")
public class Find extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Find() { }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String result = null;
		if(type.equals("1")) {
			String memail = request.getParameter("memail");
			result = MemberDao.getInstance().findID( memail );
		}
		else if(type.equals("2")) {
			String mid = request.getParameter("mid");
			String memail = request.getParameter("memail");
			
			// 임시 비밀번호 생성 (@!#난수 목록 만들어 사용!@# , 참고사항: 다른 방법으로 아스키코드 이용하여 사용할 수 있음)
			String ranStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			String updatePW = "";
			for( int i = 0; i<12; i++ ) {
				Random random = new Random();
				int ran = random.nextInt( ranStr.length() );
				updatePW += ranStr.charAt( ran );
			}
			result = MemberDao.getInstance().findPW( mid, memail, updatePW );
		}
		response.getWriter().print(result);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
