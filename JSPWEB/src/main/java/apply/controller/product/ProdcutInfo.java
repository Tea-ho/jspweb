package apply.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import apply.model.dao.MemberDao;
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
		
		// ------------------------------------------ commons.jar 라이브러리 사용 -----------------------------------------------
		request.getParameter("UTF-8");
		// 1. 다운로드할 서버 경로 찾기
		String path = request.getSession().getServletContext().getRealPath("/apply/product/pimg");
		
		// 2. 찾은 경로의 파일/폴더 객체화
		File pathObject = new File(path);
		// 목적: setRepository에 넣기 위함. (setRepository 메소드 인수로 File을 받음)
		
		// 3. 파일 저장소 객체 생성
		DiskFileItemFactory fileSave = new DiskFileItemFactory();
		// DiskFileItemFactory implements org.apache.commons.fileupload.FileItemFactory
		// 인터페이스 FileItemFactory를 구현한 DiskFileItemFactory 클래스이기 때문에 아래 ServletFileUpload 메소드가 인수로 받을 수 있음. 
		
		fileSave.setRepository(pathObject);			// 파일 저장소 객체에 파일 객체 위치 전달
		fileSave.setSizeThreshold(1024*1024*10);	// 파일 저장소 용량 설정
		fileSave.setDefaultCharset("UTF-8");		// 인코딩 설정
		
		// 4. 파일 업로드 객체 생성
		ServletFileUpload fileUpload = new ServletFileUpload(fileSave);
		// 특징: 업로드 객체에 파일 저장소 객체 전달
			// System.out.println( "fileUpload:" + fileUpload ); 	// --- 객체 주소값 확인
			// System.out.println(fileUpload.toString());			// --- 객체 주소값 확인
		
		try { // 예외처리 필수: fileUpload.parseRequest(request);
			List<FileItem> fileList = fileUpload.parseRequest(request);
				System.out.println(fileUpload.toString());
				System.out.println( fileList );
				System.out.println( fileList.get(0) );
				System.out.println( fileList.get(1) );
				System.out.println( fileList.get(2) );
				System.out.println( fileList.get(3) );
				System.out.println( fileList.get(4) );
				System.out.println( fileList.get(5) );
			List<String> fieldFileList = new ArrayList<>(); // --- 파일 필드 저장소
			List<String> fieldElseList = new ArrayList<>(); // --- 파일 필드 외 저장소
			
			for( FileItem item : fileList ) {
				// System.out.println( "필드명:" + item.getFieldName());	// --- 필드명 확인 완료
				// System.out.println( "파일명:" + item.getName());		// --- 파일명 확인 완료
				
				if( item.isFormField() ) { // isFormField() 반환값: 파일이면 false, 아니면 true
						System.out.println( "일반 필드명:" + item.getFieldName());
						System.out.println( "일반 필드값:" + item.getString());
					fieldElseList.add( item.getString() );
				} else {
						System.out.println( "첨부파일 필드명:" + item.getFieldName());
						System.out.println( "첨부파일 필드값:" + item.getName());
					
					// (추가옵션) 사용자가 업로드한 첨부파일명 -> 식별가능한 이름으로 변경 작업: UUID 적용
					String fileName = UUID.randomUUID() + " " + (item.getName().replace(" ", "-")); // 공백 제거
					// 최종 식별 파일명: UUID 파일명 (사이에 띄어쓰기 존재)
					// cos.jar에서는 DefaultFileRenamePolicy() 제공해줬기 때문에 첨부파일 변경 작업 생략함
					
					fieldFileList.add( item.getName() );
					
					File getFile = new File( path+"/"+fileName);
					
					item.write(getFile);
				}
			}
			
			int mNo = MemberDao.getInstance().getMNo( (String)request.getSession().getAttribute("login") );
			
				System.out.println( fieldElseList.get(0) );
				System.out.println( fieldElseList.get(1) );
				System.out.println( fieldElseList.get(2) );
				System.out.println( fieldElseList.get(3) );
				System.out.println( fieldElseList.get(4) );
			
			ProductDto dto = new ProductDto(fieldElseList.get(0), fieldElseList.get(1),
											Integer.parseInt( fieldElseList.get(2)),
											fieldElseList.get(3), fieldElseList.get(4), mNo, fieldFileList );
			
				System.out.println("dto: " + dto);
			
		} catch(Exception e) { System.out.println("예외 발생:" + e); }
		
		
		
		
		// ------------------------------------------ cos.jar 라이브러리 사용 ---------------------------------------------------
		/*
		String path = request.getSession().getServletContext().getRealPath("/apply/product/pimg");
		MultipartRequest multi = new MultipartRequest(
					request, path, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy()
					);
		
		String pName = multi.getParameter("pName");							System.out.println( pName );
		String pComment = multi.getParameter("pComment");					System.out.println( pComment );
		int pPrice = Integer.parseInt(multi.getParameter("pPrice"));		System.out.println( pPrice );
		String pLat = multi.getParameter("pLat");							System.out.println( pLat );
		String pLng = multi.getParameter("pLng");							System.out.println( pLng );
		
		// 첨부파일 1개 이름 가져오기
		String pFile = multi.getFilesystemName("pFile");
			System.out.println(pFile);
			
		// muliple 사용해서 첨부파일 여러개 이름 가져오기 [cos.jar에서 제공하지 않기 때문에 라이브러리 추가 필요] 
		Enumeration pFiles = multi.getFileNames();
		
		while( pFiles.hasMoreElements() ) {
			
			String s = (String)pFiles.nextElement();
				System.out.println(s);
		}
		
		ProductDto dto = new ProductDto(pName, pComment, pPrice, pLat, pLng);
		System.out.println( dto );
		
		boolean result = ProductDao.getInstance().write(dto);
		response.getWriter().print(result);
		*/
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
