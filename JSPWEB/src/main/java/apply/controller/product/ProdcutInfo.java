package apply.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import apply.model.dao.ProductDao;
import apply.model.dto.ProductDto;


@WebServlet("/apply/product/info")
public class ProdcutInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProdcutInfo() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String east = request.getParameter("east");
		String west = request.getParameter("west");
		String south = request.getParameter("south");
		String north = request.getParameter("north");
		
		ArrayList<ProductDto> result = ProductDao.getInstance().printProductList(east, west, south, north);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getSession().getServletContext().getRealPath("/apply/product/pimg");
		MultipartRequest multi = new MultipartRequest(
					request, path, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy()
					);
		
		String pName = multi.getParameter("pName");							System.out.println( pName );
		String pComment = multi.getParameter("pComment");					System.out.println( pComment );
		int pPrice = Integer.parseInt(multi.getParameter("pPrice"));		System.out.println( pPrice );
		String pLat = multi.getParameter("pLat");							System.out.println( pLat );
		String pLng = multi.getParameter("pLng");							System.out.println( pLng );
		
		ProductDto dto = new ProductDto(pName, pComment, pPrice, pLat, pLng);
		System.out.println( dto );
		
		boolean result = ProductDao.getInstance().write(dto);
		response.getWriter().print(result);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
