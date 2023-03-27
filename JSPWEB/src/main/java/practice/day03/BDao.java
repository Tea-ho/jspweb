package practice.day03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BDao {
	
	private static BDao bdao = new BDao();
	public static BDao getInstance() { return bdao; }
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private BDao() {
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
	
	// 방문록 등록 [인수: 입력받은 값, 반환: true, false]
	public boolean onWrite( BDto dto ) {
		
		String sql = "insert into ex3( bcontent, bwriter) values (?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBcontent() );
			ps.setString(2, dto.getBwriter() );
			ps.executeUpdate(); return true;
		} catch(Exception e) { System.err.println("예외 발생:" + e); }	
		return false;
	}
	
	// 모든 방문록 출력 [인수: x, 반환: list의 dto]
	public ArrayList<BDto> onlist(){
		
		ArrayList<BDto> list = new ArrayList<>();
		
		String sql = "select * from ex3";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BDto dto = new BDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(dto);
			}
		} catch(Exception e) { System.err.println("예외 발생:" + e); }
		return list;
	}

	// 삭제
	public boolean onDelete( int bNo ) {
		
		String sql = "delete from ex3 where bno = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bNo);
			ps.executeUpdate();
			return true;
		} catch(Exception e) { System.err.println("예외 발생:" + e); }
		return false;
	}
	
	public boolean onUpdate( int bNo, String newContent ) {
	
		String sql = "update ex3 set bcontent = ? where bno = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newContent);
			ps.setInt(2, bNo);
			ps.executeUpdate();
			return true;
		} catch(Exception e) { System.err.println("예외 발생:" + e); }
		return false;
	}
}
