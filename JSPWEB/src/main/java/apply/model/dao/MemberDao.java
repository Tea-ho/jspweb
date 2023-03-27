package apply.model.dao;

import java.sql.Statement;
import java.util.ArrayList;

import apply.model.dto.MemberDto;

public class MemberDao extends Dao {
	
	private static MemberDao dao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { return dao; }
	
	// 1. 회원가입
	public boolean signup( MemberDto dto ) {
		
		String sql = "insert into member(mid,mpw,mimg,memail)values(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getMid());
			ps.setString(2, dto.getMpw());
			ps.setString(3, dto.getMimg());
			ps.setString(4, dto.getMemail());
			ps.executeUpdate();
			
			// 포인트 지급 [ 회원가입한 회원번호 정보 how? ]
			rs = ps.getGeneratedKeys();
			if( rs.next() ) {
				int mno = rs.getInt(1);
				setPoint("회원가입 축하", 1000, mno);
			}
			
			return true;
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 2. 모든 회원 출력
	public ArrayList<MemberDto> mList( int startrow, int listsize, String key, String keyword ){
		ArrayList<MemberDto> list = new ArrayList<>();
		
		String sql = "";
		
		if( key.equals("") && keyword.equals("") ) {
			sql = "select * from member order by mNo limit ?, ?";
		}
		else {
			sql = "select * from member where "+key+" like '%"+keyword+"%' order by mNo limit ?, ?";
		}
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startrow);		ps.setInt(2, listsize);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto(
									rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
								);
				list.add(dto);
			}
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return list;
	}
	
	// 2-2. 회원 전체 수 구하기
	public int getTotalSize( String key, String keyword ) {
		String sql = "";
		
		if( key.equals("") && keyword.equals("") ) { // 검색 없을 때
			sql = "select count(*) from member";
		}
		else {
			sql = "select count(*) from member where "+key+" like '%"+keyword+"%'";
		}
		try {
			ps = con.prepareStatement(sql);	rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return 0;
	}
	
	
	// 3. 아이디 중복검사
	public boolean idCheck( String mid ) {
		String sql = "select * from member where mid = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 , mid );
			rs = ps.executeQuery();
			if ( rs.next() ) { return true;}
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
	
	// 4. 로그인
	public boolean login( String mid, String mpw) {
		
		String sql = "select * from member where mid =? and mpw =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mpw);
			rs = ps.executeQuery();
			
			if ( rs.next() ) { return true; }
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
	
	// 5. 회원 정보 전송
	public MemberDto getMember( String mid ) {
		
		String sql = "select m.mno , m.mid , m.mimg , m.memail , sum( p.mpamount ) as  mpoint "
				+ "from member m , mpoint p "
				+ "where m.mno = p.mno and m.mid = ? group by mno;";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if( rs.next() ) {
				MemberDto dto = new MemberDto( rs.getInt(1), rs.getString(2), null, rs.getString(3), rs.getString(4) );
				dto.setMpoint( rs.getInt(5));
				return dto;
			} 
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return null;
	}
	
	// 6. 아이디 찾기
	public String findID( String memail ) {
		
		String sql = "select mid from member where memail = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, memail);
			rs = ps.executeQuery();
			if( rs.next() ) { return rs.getString(1); }
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return "false";
	}
	
	// 7. 비밀번호 찾기
	public String findPW( String mid, String memail, String updatePW ) {	
		String sql = "select mno from member where mid = ? and memail = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, memail);
			rs = ps.executeQuery();
			if( rs.next() ) { 
				
				sql = "update member set mpw = ? where mno = ? ";
				ps = con.prepareStatement(sql);
				ps.setString(1, updatePW);
				ps.setInt(2, rs.getInt(1));
				int result = ps.executeUpdate();
				if ( result == 1 ) {
					
					// 실제 이메일 활용할 경우 코드
					// new MemberDto().sendEmail(memail, updatePW);
					// return "true";
					
					return updatePW;
					
				}
			}
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return "false";
	}
	
	// 8. 포인트 지급
	// 적용시점1 - 회원가입 성공할 경우, 가입 축하 포인트 지급 
	public boolean setPoint( String content, int point, int mno) {
		String sql = "insert into mpoint( mpcomment, mpamount, mno) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, point);
			ps.setInt(3, mno);
			ps.executeUpdate();
			return true;
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
	
	// 9. 회원탈퇴
	public boolean delete( String mid, String mpw ) {
		String sql = "delete from member where mid = ? and mpw = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mpw);
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
	
	// 10. 회원수정
	public boolean update( String mid, String mpw, String newmimg, String newmpw, String memail ) {
		String sql = "update member set mpw = ?, memail = ?, mimg = ? where mid = ? and mpw = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newmpw);
			ps.setString(2, memail);
			ps.setString(3, newmimg);
			ps.setString(4, mid);
			ps.setString(5, mpw);
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
	
	// 11. 회원 ID -> mNo 변환 메소드
	public int getMNo(String mId) {
		String sql = "select mNo from member where mId = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mId);
			rs = ps.executeQuery();
			if( rs.next() ) { return rs.getInt(1); }
		}catch(Exception e) { System.out.println( "예외발생: " + e); }
		return 0;
	}
}
