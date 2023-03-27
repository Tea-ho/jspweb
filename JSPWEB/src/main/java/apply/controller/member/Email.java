package apply.controller.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apply.model.dto.MemberDto;

@WebServlet("/apply/Email")
public class Email extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Email() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 받을 회원 이메일 요청
		String memail = request.getParameter("memail");
			// System.out.println(memail);
		String auth = "";
		// 용도: 인증코드로 사용
		
		for( int i = 0; i < 6; i++ ) {
			Random random = new Random();	auth += random.nextInt(10);
		}
		
		// 2. SMTP(간이 메일 전송 프로토콜): Simple Mail Transfer Protocol
		// 1) 필요한 라이브러리: java-mail , java-activation
		// 2) 원리: 위의 라이브러리 이용하여, 서버( 네이버, 다음, 구글)에 메일 전송		
		
		boolean result = new MemberDto().sendEmail(memail, auth);
		if( result ) { response.getWriter().print(auth); }
		else { response.getWriter().print(result); }
	}
}
