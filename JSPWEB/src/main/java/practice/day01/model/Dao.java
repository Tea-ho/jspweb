package practice.day01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	private static Dao dao = new Dao();
	public static Dao getInstance() { return dao; }
	
	private Dao() {
		try {
			// 웹서버 작업 시, 해당 mysql 드라이버[라이브러리] 찾기!
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jspweb",
					"root",
					"1234"
					);
			System.out.println("연동 성공");
			
		} catch(Exception e) { System.err.println("예외 발생:" + e); }		
	}
	
	// SQL 메소드
	public boolean setData(String data) {
		// 1. SQL 작성
		String sql = "insert into ex1 values(?)";
		
		try {
			// 2. SQL 대입
			ps = con.prepareStatement(sql);
			// 3. SQL 조작
			ps.setString(1, data);
			// 4. SQL 결과
			ps.executeUpdate();
			// 5. 응답
			return true;
		} catch(Exception e) { System.err.println("예외 발생:" + e );}
		return false;
	}
	
	public ArrayList<String> getData(){
		
		ArrayList<String> list = new ArrayList<>();
		
		String sql = "select * from ex1";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
		} catch(Exception e) { System.err.println("예외 발생:" + e );}
		return list;
	}
	
	// 과제1
	public boolean sethw(String data) {
		
		String sql = "insert into hw1 values(?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,data);
			ps.executeUpdate();
			return true;
		}catch(Exception e) { System.out.println("예외 발생:" + e);}
		return false;
	}
	
	public ArrayList<String> gethw(){
		
		ArrayList<String> hwList = new ArrayList<>();
		
		String sql = "select * from hw1";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) { hwList.add(rs.getString(1)); }
			
		}catch(Exception e) { System.err.println("예외 발생:" + e );}
		return hwList;
	}
	
	
	
	
	
	
}
