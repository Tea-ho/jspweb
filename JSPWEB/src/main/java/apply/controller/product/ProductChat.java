package apply.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import apply.controller.admin.Alarm;
import apply.model.dao.MemberDao;
import apply.model.dao.ProductDao;
import apply.model.dto.ProductchatDto;

@WebServlet("/apply/productchat")
public class ProductChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductChat() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt(request.getParameter("pno"));
		int mno = MemberDao.getInstance().getMNo((String)request.getSession().getAttribute("login"));
		int chatmno = Integer.parseInt(request.getParameter("chatmno"));
		
		ArrayList<ProductchatDto> result = ProductDao.getInstance().getChatList(pno, mno, chatmno);
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray =  mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ncontent = request.getParameter("ncontent");				System.out.println( "ncontent:" + ncontent );
		int pno = Integer.parseInt(request.getParameter("pno"));
		int frommno = MemberDao.getInstance().getMNo((String)request.getSession().getAttribute("login"));
		int tomno = Integer.parseInt(request.getParameter("tomno"));
			System.out.println("tomno:" + tomno);
		ProductchatDto dto = new ProductchatDto(0, ncontent, null, pno, frommno, tomno);
		System.out.println(dto.toString());
		
		boolean result = ProductDao.getInstance().setChat( dto );
		
		// (추가옵션 - 소캣 활용) 채팅 등록 성공 시, tomno에게 소캣 알림 메시지 보내기
		if( result ) {
			try {
				Alarm.onMessage(null, tomno+","+ncontent); // --- 서버 소켓에게 채팅을 받은 유저 번호와 내용 전달
			}catch (Exception e) {	System.out.println("예외 발생:" + e);}
		}
		response.getWriter().print(result);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
