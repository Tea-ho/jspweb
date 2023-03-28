package apply.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	
		ArrayList<ProductchatDto> result = ProductDao.getInstance().getChatList(pno, mno);
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
		
		ProductchatDto dto = new ProductchatDto(0, ncontent, null, pno, frommno, tomno);
		System.out.println(dto.toString());
		
		boolean result = ProductDao.getInstance().setChat( dto );
		response.getWriter().print(result);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
