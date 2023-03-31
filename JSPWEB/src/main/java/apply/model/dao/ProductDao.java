package apply.model.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import apply.model.dto.ProductDto;
import apply.model.dto.ProductchatDto;

public class ProductDao extends Dao {
	
	private static ProductDao dao = new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() { return dao; }
	
	// synchronized: 동기식으로 설정 
	// 1. 제품 등록 
	public synchronized boolean write( ProductDto dto ) { 
		// 1. 제품 우선 등록 
		String sql ="insert into product(pname, pcomment, pprice, plat, plng , mno )"
				+ " values(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS ); // PK키 가져오기
			ps.setString(1 , dto.getPname() );	ps.setString(2 , dto.getPcomment() );
			ps.setLong(3 , dto.getPprice() );	ps.setString(4 , dto.getPlat() );
			ps.setString(5 , dto.getPlng() );	ps.setInt( 6 , dto.getMno() );
			ps.executeUpdate();  
			// insert 후 생성된 제품pk번호 호출 [ pno 구하기 ]
			rs = ps.getGeneratedKeys();
			if( rs.next() ) { // 만약에 생성된 제품 pk번호가 존재하면 
				// dto내 첨부파일명 리스트에서 하나씩 첨부파일명을 insert 하기
				for( String pimgname : dto.getPimglist() ) {
					sql = "insert into pimg( pimgname , pno ) values(?,?)";
					ps = con.prepareStatement(sql);
					ps.setString( 1 ,pimgname ); ps.setInt( 2 , rs.getInt(1) );
					ps.executeUpdate();
				}
			}
			return true;
		}catch (Exception e) { 	System.out.println(e); 	} return false;
	}
	
	// 2. 제품 호출
	public synchronized ArrayList<ProductDto> getProductList( String 동 , String 서 , String 남 , String 북 ){
		
		System.out.println( 동);
		System.out.println( 서);
		System.out.println( 남);
		System.out.println( 북);
		
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = "select p.*, m.mId, m.mimg from product p natural join member m where ? >= pLng and ? <= pLng and ? <= pLat and ? >= pLat";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 , 동 );	ps.setString( 2 , 서 );	ps.setString( 3 , 남 );	ps.setString( 4 , 북 );
			
			rs = ps.executeQuery();
			System.out.println("ㄴㅇㄹ");
			while( rs.next() ) {
				System.out.println("ㄴㅇㄹ");
				// 사진 레코드 호출 
				ArrayList<String> pimglist = new ArrayList<>();
				sql = "select * from pimg where pno = "+rs.getInt(1); // 동일한 제품번호의 이미지들을 호출
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				
				while( rs2.next() ) {
					pimglist.add( rs2.getString(2) );	// 검색된 이미지이름을 리스트에 저장 
				}
				
				ProductDto dto = new ProductDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getInt(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getString(9), 
						rs.getInt(10), rs.getString(11), rs.getString(12), pimglist );
				
				// System.out.println( dto.getMid() );
				
				list.add( dto );
			}
			System.out.println(list.toString());
		}catch (Exception e) { 	System.out.println(e); 	} return list;
	}
	
	// 3. 찜하기 등록/취소 
	public synchronized boolean setplike( int pno , int mno ) {
		// 1. 등록할지 취소할지 검색 먼저하기 
		String sql ="select * from plike where pno = "+pno+" and mno = "+mno;
		try {
			ps = con.prepareStatement(sql);	rs = ps.executeQuery();
			if( rs.next() ) { // 해당 회원이 이미 찜하기를 한 제품 ---> 취소하기 
				sql = "delete from plike where pno = "+pno+" and mno = "+mno;
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				return false;	// 취소 되었을떄
			}else {	// 해당 회원이 찜하기를 하지 않은 제품 ----> 등록하기 
				sql = "insert into plike( pno , mno )values( "+pno+" , "+mno+" )";
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				return true;	// 등록 되었을때
			}
		}catch (Exception e) { System.out.println(e); }  return false;
	}
	
	// 4. 현재 회원이 해당 제품의 찜하기 상태 확인 
	public synchronized boolean getplike( int pno , int mno ) {
		String sql ="select * from plike where pno = "+pno+" and mno = "+mno;
		try {
			ps = con.prepareStatement(sql);	rs = ps.executeQuery();
			if( rs.next() ) { return true; }
		}catch (Exception e) { 	System.out.println(e); 	}  return false;
	}
	
	// 5. Chat 저장
	public synchronized boolean setChat( ProductchatDto dto ) {
		String sql = "insert into note( ncontent, pno, frommno, tomno) values (?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getNcontent());
			ps.setInt(2, dto.getPno());
			ps.setInt(3, dto.getFrommno());
			ps.setInt(4, dto.getTomno());
			ps.executeUpdate();
			return true;
		} catch (Exception e) { System.out.println(e); }  return false;
	}
	
	// 6. Chat 출력
	public synchronized ArrayList<ProductchatDto> getChatList( int pno, int mno, int chatmno ){
		
		ArrayList<ProductchatDto> list = new ArrayList<>();
		
		// 모든 데이터 가져옴
		// String sql = "select * from note where pno = ? and (frommno = ? or tomno = ?)";
		// 문제점: 판매자 채팅에서 모든 구매희망자 메시지가 모두 출력됨
		// 해결: sql 수정
		
		// 현재 같이 채팅하고 있는 데이터만 출력
		// String sql = "select * from note where pno = ? and ((frommno = ? and tomno =?) or (frommno = ? and tomno =?)) ";
		
		String sql = "";
		if( chatmno != 0 ) { 
			sql = "select * from note where pno = ? and ((frommno = ? and tomno =?) or (frommno = ? and tomno =?))";
		} else {
			sql = "select * from note where pno = ? and (frommno = ? or tomno = ?)";
		}
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pno); ps.setInt(2, mno);
			if( chatmno != 0 ) {
				ps.setInt(3, chatmno); ps.setInt(4, chatmno);	ps.setInt(5, mno);
			}
			else {
				ps.setInt(3, mno);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductchatDto dto = new ProductchatDto(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getInt(6)
						);
				
				sql = "select mid, mimg from member where mno = "+rs.getInt(5);
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				if( rs2.next() ) {
					dto.setFrommid(rs.getString(1));
					dto.setFrommimg(rs.getString(2));
				}
				list.add( dto );
			}	
		} catch (Exception e) { System.out.println(e); }
		return list; 
	}
	
	// 7. 날짜별 포인트 충전 내역
	public HashMap<String, Integer> getSum(){ // HashMap: 원하는 타입으로 데이터 2개 저장 가능
		
		HashMap<String, Integer> map = new HashMap<>();		
		String sql = "select "
				+ " sum(if( mpcomment = '포인트구매', mpamount, 0 ))  as 충전포인트, "
				+ " date_format( mpdate, '%y%m%d' ) as 충전날짜 "
				+ " from mpoint "
				+ " group by date_format( mpdate, '%y%m%d' ) "
				+ " order by 충전날짜 desc "
				+ " limit 5";
		try {
			ps = con.prepareStatement(sql);		rs = ps.executeQuery();
			while(rs.next()) {
				map.put( rs.getString(2), rs.getInt(1) );
			} 
		} catch(Exception e){ System.err.println(e); }
		return map;
	}
}