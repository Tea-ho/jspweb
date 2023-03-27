package apply.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	
	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;

	public Dao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspweb","root","1234");
		} catch (Exception e) { System.out.println("예외발생: " + e); }
		/*	
		 	MySQL JDBC 드라이버가 시스템에 설치되어 있으면 Class.forName() 메서드를 사용하여 드라이버를 로드하고, 
		 	DriverManager.getConnection() 메서드를 사용하여 MySQL 데이터베이스에 연결할 수 있음
		 	이렇게 연결된 Connection 객체를 이용하여 데이터베이스에 쿼리를 실행하고 결과를 받아올 수 있음		 	
		*/
	}
}
