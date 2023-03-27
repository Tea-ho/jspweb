package apply.controller.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/api/Api01ElectricCharginAnsan")
public class Api01ElectricCharginAnsan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Api01ElectricCharginAnsan() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 공공데이터 포털에서 신청한 데이터 가져오기: URL 클래스 활용
		URL url = new URL("https://api.odcloud.kr/api/15090398/v1/uddi:6fe0e3f2-0285-4999-9edf-995afe19a6ea?page=1&perPage=96&serviceKey=z427Q0DLkQqM0SDOc1Lz8jPzk%2BKj0ng%2Bvz7h3I8CpVs3T90219bWi2o%2BmStIxJW%2B9lwayA%2FsAT6apxsxuvydQg%3D%3D");
		
		
		// 2. 스트림객체 생성하기
		// 1) InputStream 클래스 이용
		// InputStream inputStream = url.openStream();
		
		// 2) InputStreamReader 클래스 이용
		// URL 클래스 특징: openStream() 메소드 제공
		// InputStreamReader reader = new InputStreamReader( url.openStream(), "UTF-8" );		
		// 해석1: 스트림화하여 데이터를 바이트로 가져옴
		// 해석2: UTF-8로 인코딩 진행
		
		// 3) BufferedReader 클래스 이용
		BufferedReader bf = new BufferedReader( new InputStreamReader(url.openStream(), "UTF-8") );
		
			// byte[] array1 = new byte[100000];
			// 목적: inputStream을 이용하기 위해서는 byte 배열 필요
			// char[] array2 = new char[100000];
			// 목적: InputStreamReader을 이용하기 위해서는 char 배열 필요
		
		// 3. 스트림화한 객체 바이트로 읽어오기
		// inputStream.read( array1 );		// 특징: byte 배열로 읽을 수 있음
		// reader.read( array2 );			// 특징: 문자char 배열로 읽을 수 있음
		String result = bf.readLine();		// 특징: String으로 바로 읽을 수 있음 [배열 필요 없음]
		
		// System.out.println( array1 );
		// System.out.println( array2 );
		System.out.println( result );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
