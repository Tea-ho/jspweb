package practice.과제.과제01;

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
	
	public boolean submit( Dto dto ) {
		String sql = "insert into student( sName,sPhone,sLength,sAge,sDate,sSex,sHome,sIntro,sAgree ) values( ?,?,?,?,?,?,?,?,? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getsName());
			ps.setString(2, dto.getsPhone());
			ps.setDouble(3, dto.getsLength());
			ps.setInt(4, dto.getsAge());
			ps.setString(5, dto.getsDate());
			ps.setString(6, dto.getsSex());
			ps.setString(7, dto.getsHome());
			ps.setString(8, dto.getsIntro());
			ps.setBoolean(9, dto.issAgree());
			ps.executeUpdate();	
			return true;
		} catch(Exception e) { System.err.println("예외 발생:" + e); }		
		return false;
	}
	
	public ArrayList<Dto> sList(){
		
		ArrayList<Dto> list = new ArrayList<>();
		String sql = "select * from student";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				Dto dto = new Dto( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
								rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10)
								);
				list.add(dto);
			}
		} catch(Exception e) { System.err.println("예외 발생:" + e); }		
		return list;
	}
	
}
