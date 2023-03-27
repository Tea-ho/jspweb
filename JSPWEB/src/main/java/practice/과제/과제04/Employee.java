package practice.과제.과제04;

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

import practice.과제.과제04.dao.EmployeeDao;
import practice.과제.과제04.dto.EmployeeDto;

@WebServlet("/employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Employee() {	super(); }
    
    // [R] 인사 정보 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<EmployeeDto> result = EmployeeDao.getInstance().getEmployeeList();
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);	
	}
	
	// [C] 직원 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 서버에 배포된 프로젝트 내 폴더 경로
		String uploadpath = request.getServletContext().getRealPath("/practice/과제/과제04/employee/eimg");
		
		// 업로드
		MultipartRequest multi = new MultipartRequest(
				request,
				uploadpath,
				1024*1024*10 ,
				"UTF-8" ,
				new DefaultFileRenamePolicy() );
		// 그외 매개변수
		String empImg = multi.getFilesystemName("empImg");				System.out.println(empImg);
		String empName = multi.getParameter("empName");					System.out.println(empName);		
		String empGrade = multi.getParameter("empGrade");				System.out.println(empGrade);
		String empConstruct = multi.getParameter("empConstruct");		System.out.println(empConstruct);
		String empDepart = multi.getParameter("empDepart");				System.out.println(empDepart);
		String empSdate = multi.getParameter("empSdate");				System.out.println(empSdate);
		String empLdate = multi.getParameter("empLdate");				
		String empLcomment = multi.getParameter("empLcomment");			
		
		if( empLdate.equals("")) { empLdate = null; }					System.out.println(empLdate);
		if( empLcomment.equals("")) { empLcomment = null; }				System.out.println(empLcomment);
		
		EmployeeDto dto = new EmployeeDto(0, empImg, empName, empGrade, empConstruct, empDepart, empSdate, empLdate, empLcomment);
		
		boolean result = EmployeeDao.getInstance().signup(dto);
		response.getWriter().print(result);
	}
	
	// [U] Update
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadpath = request.getServletContext().getRealPath("/practice/과제/과제04/employee/eimg");
		
			System.out.println(uploadpath);
		
		MultipartRequest multi = new MultipartRequest(
				request,						// 요청방식 
				uploadpath,						// 첨부파일 저장할 서버 폴더
				1024*1024*10,					// 첨부파일 용량 설정 [단위 byte로 설정되어 있음]
				"UTF-8",						// 인코딩 타입 설정
				new DefaultFileRenamePolicy()	// 동일한 첨부파일명으로 중복 업로드 될 경우, 식별이 불가능해지는 상황 예방하는 옵션
												// 작동원리: 동일 첨부명 존재하면 뒤에 숫자 자동 부여
		);
		
		int empNo = Integer.parseInt(multi.getParameter("empNo"));	System.out.println( empNo );
		String empImg = multi.getFilesystemName("newImg");			System.out.println( empImg );
		String empName = multi.getParameter("newName");				System.out.println( empName );
		String empGrade = multi.getParameter("newGrade");			System.out.println( empGrade );
		String empContruct = multi.getParameter("newConstruct");	System.out.println( empContruct );
		String empDepart = multi.getParameter("newDepart");			System.out.println( empDepart );
		String empSdate = multi.getParameter("newSdate");			System.out.println( empSdate );
		String empLdate = multi.getParameter("newLdate");			System.out.println( empLdate );
		String empLcomment = multi.getParameter("newLcomment");		System.out.println( empLcomment );
		
		if( empImg == null ) {
			empImg = EmployeeDao.getInstance().printInfo(empNo).getEmpImg();
		}
		
		boolean result = EmployeeDao.getInstance().doUpdate(empNo, empImg, empName, empGrade, empContruct, empDepart, empSdate, empLdate, empLcomment);
		response.getWriter().print(result);
		
	}
	
	// [D] Delete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int empNo =  Integer.parseInt(request.getParameter("empNo"));	
		boolean result = EmployeeDao.getInstance().doDelete(empNo);
		response.getWriter().print(result);
	}
}
