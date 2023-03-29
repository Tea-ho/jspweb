package apply.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apply.model.dao.MemberDao;
import apply.model.dao.ProductDao;
import apply.model.dto.ProductDto;

/**
 * Servlet implementation class ProductLike
 */
@WebServlet("/apply/ProductLike")
public class ProductLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductLike() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 찜하기 가져오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pNo = Integer.parseInt( request.getParameter("pno") );
		int mNo = MemberDao.getInstance().getMNo(
				(String)request.getSession().getAttribute("login")
				);
		System.out.println(pNo);	System.out.println(mNo);
		
		boolean result = ProductDao.getInstance().getplike(pNo, mNo);
		response.getWriter().print(result);
	}

	// 찜하기 등록 / 취소
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pNo = Integer.parseInt( request.getParameter("pno") );
		int mNo = MemberDao.getInstance().getMNo(
				(String)request.getSession().getAttribute("login")
				);
		System.out.println(pNo);	System.out.println(mNo);
		
		boolean result = ProductDao.getInstance().setplike(pNo, mNo);
		response.getWriter().print(result);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
