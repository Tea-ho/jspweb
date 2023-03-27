package practice.과제.과제04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import practice.과제.과제04.dto.EmployeeDto;

public class EmployeeDao {
	
	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	private static EmployeeDao dao = new EmployeeDao();
	private EmployeeDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_ass1","root","1234");
		} catch (Exception e) { System.out.println("예외발생: " + e); }
	}
	public static EmployeeDao getInstance() { return dao; }
	
	public boolean signup( EmployeeDto dto ) {
		String sql = "insert into emp_info(empImg , empName , empGrade , empConstruct , empDepart , empSdate , empLdate , empLcomment) values ( ? ,? ,? ,? ,? ,? ,? ,? ) "; 
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getEmpImg());
			ps.setString(2, dto.getEmpName());
			ps.setString(3, dto.getEmpGrade());
			ps.setString(4, dto.getEmpConstruct());
			ps.setString(5, dto.getEmpDepart());
			ps.setString(6, dto.getEmpSdate());
			ps.setString(7, dto.getEmpLdate());
			ps.setString(8, dto.getEmpLcomment());
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	} // 인사 등록 end

	// 2.1 인사 전체출력
	public ArrayList<EmployeeDto> getEmployeeList(){
		ArrayList<EmployeeDto> list = new ArrayList<>();
		String sql = "select * from emp_info";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				EmployeeDto dto = new EmployeeDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getString(8), rs.getString(9) );
			list.add(dto);
			}
		
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 2.2 인사 직무(부서별) 출력
	public ArrayList<EmployeeDto> getEmployeejob( String empDepart ){
		ArrayList<EmployeeDto> list2 = new ArrayList<>();
		String sql = "select e.empDepart , e.empGrade , e.empName  from emp_info e where empDepart = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, empDepart);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				EmployeeDto dto2 = new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3));
			list2.add(dto2);
			}
		}catch (Exception e) {System.out.println(e);}
		return list2;
	} 
	
	// 회원 정보 삭제
	public boolean doDelete( int empNo ) {
		
		String sql = "delete from emp_info where empNo = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, empNo);
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
	
	// 일부 회원 정보 출력
	public EmployeeDto printInfo( int empNo ) {
		String sql = "select * from emp_info where empNo = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, empNo);
			rs = ps.executeQuery();
			if( rs.next() ) {
				EmployeeDto dto = new EmployeeDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getString(8), rs.getString(9) );
				return dto;
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 회원 정보 수정
	public boolean doUpdate( int empNo, String empImg, String empName, String empGrade, String empConstruct, String empDepart, String empSdate, String empLdate, String empLcomment ) {
		String sql = "update emp_info set empImg = ?, empName = ?, empGrade = ?, empConstruct = ?, empDepart = ?, empSdate = ?, empLdate = ?, empLcomment = ? where empNo = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, empImg);
			ps.setString(2, empName);
			ps.setString(3, empGrade);
			ps.setString(4, empConstruct);
			ps.setString(5, empDepart);
			ps.setString(6, empSdate);
			ps.setString(7, empLdate);
			ps.setString(8, empLcomment);
			ps.setInt(9, empNo);
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
}
