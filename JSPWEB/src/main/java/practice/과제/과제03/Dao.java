package practice.과제.과제03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {
	
	private static Dao dao = new Dao();
	public static Dao getInstance() { return dao; }
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
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
	
	public boolean onAdd( Dto dto ) {
		
		String sql = "insert into hw3(pTitle,pPrice,pContent) values(?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getpTitle());
			ps.setInt(2, dto.getpPrice());
			ps.setString(3, dto.getpContent());
			ps.executeUpdate(); return true;
		}catch(Exception e) { System.err.println("예외 발생:" + e); }	
		return false;
	}
	
	public ArrayList<Dto> onList(){
		
		ArrayList<Dto> pList = new ArrayList<>();
		
		
		String sql = "select * from hw3";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Dto dto = new Dto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				pList.add(dto);
			}
		}catch(Exception e) { System.err.println("예외 발생:" + e); }
		return pList;
	}
	
	public boolean onUpdate( int pNo, String pTitle, int pPrice, String pContent ) {
		
		String sql = "update hw3 set pTitle = ?, pPrice = ? , pContent = ? where pNo = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pTitle);
			ps.setInt(2, pPrice);
			ps.setString(3, pContent);
			ps.setInt(4, pNo);
			ps.executeUpdate(); return  true;
		} catch(Exception e) { System.err.println("예외 발생:" + e); }	
		return false;
	}
	
	public boolean onDelete( int pNo ) {
		
		String sql = "delete from hw3 where pNo =?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, pNo);
			ps.executeUpdate();
			return true;
		} catch(Exception e) { System.err.println("예외 발생:" + e); }
		return false;
		
	}
}
