package apply.controller.board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/apply/FileDown")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDown() { }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 다운로드 할 파일명 요청
		request.setCharacterEncoding("UTF-8");
		String bFile = request.getParameter("bFile");
			System.out.println( "bFile: " + bFile );
		
		// 2. 다운로드할 폴더 경로 찾기
		String path = request.getSession().getServletContext().getRealPath("/apply/board/bfile");
			System.out.println( "path: " + path );
			
		// 3. 다온로드할 폴더의 파일 경로 찾기
		String pathFile = request.getSession().getServletContext().getRealPath("/apply/board/bfile/"+bFile);
			System.out.println( "pathFile: " + pathFile );
		
		// 4. 파일 클래스
		File file = new File(pathFile);
		
		// (옵션) 다운로드 완료 형식 적용 [각 브라우저에서 제공해주는 메소드] 
		response.setHeader(
		// setHeader method: HTTP 옵션정보 설정
					"Content-Disposition" ,
					// 기능: 각 브라우저마다 다운로드형식 HTTP 옵션에 포함해서 내보내기!
					"attachment;filename="+URLEncoder.encode(bFile, "UTF-8")
					// 기능: 다운로드 시 파일명 표시!
				);
		
		// 5-1. 파일 스트림[바이트단위]: 파일 객체의 바이트를 모두 읽어오기
		FileInputStream fin = new FileInputStream( file );
		
		// 5-2. 스트림객체에서 꺼내온 바이트를 저장할 바이트 배열 생성 
		byte[] bytes = new byte[ (int)file.length() ];
		
		// 5-3. 파일을 바이트로 읽어오기: read() 메소드 사용
		fin.read( bytes );
		
		// 5-4. 읽어온 바이트 출력 후 클라이언트에게 응답
		BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() );
		
		// 5-5. 파일 객체의 바이트를 내보내기: write() 메소드 사용
		fout.write(bytes);
		
		// 5-6. 파일입력스트림 객체 초기화 (안전장치)
		fin.close();
		
		// 5-7. 파일출력스트림 객체의 메모리 초기화 (안전장치)
		fout.flush();
		
		// 5-8. 파일출력스트림 객체 초기화 (안전장치)
		fout.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
