package apply.model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import apply.model.dto.BoardDto;
import apply.model.dto.ReplyBoardDto;

public class BoardDao extends Dao {

	private static BoardDao dao = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() { return dao; }
	
	// 1. 글쓰기
	public boolean bWrite( BoardDto dto ) {
		String sql = "insert into board( bTitle, bContent, bFile, mNo, cNo ) values( ?, ?, ?, ?, ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getbTitle() );	ps.setString(2, dto.getbContent() );
			ps.setString(3, dto.getbFile() );	ps.setInt(4, dto.getmNo() );
			ps.setInt(5, dto.getcNo() );		ps.executeUpdate();
			return true;
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 2-1. 모든 글 출력
	public ArrayList<BoardDto> getBoardList( int startrow, int listsize, String key, String keyword, int cNo ){
		
		ArrayList<BoardDto> list = new ArrayList<>();
		
		String sql = "";
		if( key.equals("") && keyword.equals("") ) {
			sql = "select b.*, m.mid, m.mimg from member m natural join board b where b.cNo = "+cNo+" order by b.bDate limit ?, ?";
		}
		else {
			sql = "select b.*, m.mid, m.mimg from member m natural join board b where "+key+" like '%"+keyword+"%' and b.cNo = "+cNo+" order by b.bDate limit ?, ?";
			// sql 2가지 type 형태 통일시키기 위해 key와 keyword는 ? 사용하지 않음.
		}
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startrow);		ps.setInt(2, listsize);
			rs = ps.executeQuery();
			while( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12));
				
				sql = "select count(*) from reply where bNo ="+dto.getbNo();
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				if( rs2.next() ) { dto.setrCount( rs2.getInt(1) ); }
				list.add(dto);
			}
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return list;
	}
	
	// 2-2. 게시물 수 구하기
	public int getTotalSize( String key, String keyword, int cNo ) {
		
		String sql = "";
		
		if( key.equals("") && keyword.equals("") ) { // 검색 없을 때
			sql = "select count(*) from member m natural join board b where b.cNo ="+cNo;
		}
		else {
			sql = "select count(*) from member m natural join board b where "+key+" like '%"+keyword+"%' and b.cNo ="+cNo;
			// sql 2가지 type 형태 통일시키기 위해 ? 사용하지 않음.
		}
		try {
			ps = con.prepareStatement(sql);	rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return 0;
	}
	
	// 3. 개별 출력
	public BoardDto getBoard( int bNo ) {
		
		String sql = "select b.*, m.mid, m.mimg from board b natural join member m where b.bno = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bNo);
			rs = ps.executeQuery();
			if( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12));
				return dto;
			}
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return null;
	}
	
	// 4. 조회수, 좋아요, 싫어요 증가 [update]
	public boolean bIncrease( int type, int bNo ) {
		String sql = "";
		
		if( type == 1 ) { sql = "update board set bView = bView+1 where bNo ="+bNo; }
		if( type == 2 ) { sql = "update board set bUp = bUp+1 where bNo ="+bNo; }
		if( type == 3 ) { sql = "update board set bDown = bDown+1 where bNo ="+bNo; }
		
		try {
			ps = con.prepareStatement(sql); ps.executeUpdate(); return true;
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 5-1. 게시물 삭제
	public boolean bdelete( int bNo ) {
		
		String sql = "delete from board where bNo ="+bNo;
		
		try{
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if( count == 1 ) return true;
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 5-2. 첨부파일만 삭제
	public boolean bfileDelete(int bNo) {
		
		String sql = "update board set bFile = null where bNo="+bNo;
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if( count == 1 ) return true;			
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
		
	}
	
	// 6. 게시글 업데이트
	public boolean bupdate( BoardDto updatedto ) {
		
		String sql = "update board set bTitle = ?, bContent = ?, bFile = ?, cNo = ? where bNo = ?";
		
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, updatedto.getbTitle());
			ps.setString(2, updatedto.getbContent());
			ps.setString(3, updatedto.getbFile());
			ps.setInt(4, updatedto.getcNo());
			ps.setInt(5, updatedto.getbNo());
			int count = ps.executeUpdate();
			if( count == 1 ) return true;
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 7-1. 댓글 작성
	public boolean rwrite( ReplyBoardDto dto ) {
		
		try {
			String sql = "";
			if( dto.getrIndex() == 0 ) { // 부모 댓글 SQL
				sql = "insert into reply(rContent,mNo,bNo)values(?,?,?)";
			}
			else { // 자식 댓글 SQL
				sql = "insert into reply(rContent,mNo,bNo,rIndex)values(?,?,?,?)";
			}
			
			// 공통 적용 사항
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getrContent());
			ps.setInt(2, dto.getmNo());
			ps.setInt(3, dto.getbNo());
			
			// 자식 댓글의 경우, 추가 적용 사항
			if( dto.getrIndex() != 0 ){ ps.setInt(4, dto.getrIndex()); }
			
			ps.executeUpdate(); return true;
			
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 7-2. 댓글 출력
	public ArrayList<ReplyBoardDto> getReplyList( int bNo, int rIndex ){
		ArrayList<ReplyBoardDto> list = new ArrayList<>();;
		try {
			
			String sql = ""; 
			if( rIndex == 0 ) {
				sql = "select r.*, m.mId, m.mImg from reply r natural join member m where r.rIndex = 0 and bNo = "+bNo;
			}
			else {
				sql = "select r.*, m.mId, m.mImg from reply r natural join member m where r.rIndex = "+rIndex+" and bNo = "+bNo;
			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				ReplyBoardDto dto = new ReplyBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3),
													rs.getInt(4), rs.getInt(5), rs.getInt(6),
													rs.getString(7), rs.getString(8));
				sql = "select count(*) from reply where bNo ="+bNo;
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				if( rs2.next() ) { dto.setrCount( rs2.getInt(1) ); }
				list.add(dto);
			}
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return list;
	}
	
	// 7-3. 댓글 삭제
	public boolean deleteReply() {
		
		String sql = "delete from reply where mNo = ?";
		try {
			ps = con.prepareStatement(sql);
			
			return true;
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
		
	}
	
	// 7-4. 댓글 수정
	public boolean editReply() {
		
		String sql = "update reply set rContent = ? where mNo = ? and bNo = ?";
		try {
			ps = con.prepareStatement(sql);
			
			return true;
		} catch(Exception e) { System.out.println( "예외발생: " + e); }
		return false;
	}
}
