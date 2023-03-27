package apply.model.dao;

import java.util.ArrayList;

import apply.model.dto.ProductDto;

public class ProductDao extends Dao {
	

	private static ProductDao dao = new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() { return dao; }

	// 1. 제품 등록
	public boolean write( ProductDto dto ) {
		String sql = "insert into product(  pName, pComment, pPrice, pLat, pLng) values( ?, ?, ?, ?, ? )";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getpName());	ps.setString(2, dto.getpComment());
			ps.setInt(3, dto.getpPrice());		ps.setString(4, dto.getpLat());
			ps.setString(5, dto.getpLng());		ps.executeUpdate();
			return true;
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 2. 제품 출력
	public ArrayList<ProductDto> printProductList( String east, String west, String south, String north){
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = "select * from product where ? >= pLng and ? <= pLng and ? <= pLat and ? >= pLat";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, east); ps.setString(2, west); ps.setString(3, south); ps.setString(4, north);
			rs = ps.executeQuery();
			while( rs.next() ) {
				list.add( new ProductDto(rs.getInt(1), rs.getString(2), rs.getString(3),
										rs.getInt(4), rs.getInt(5), rs.getString(6),
										rs.getString(7), rs.getInt(8), rs.getString(9)) );
			}
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return list;
	}
	
	// 3. 찜하기 등록 / 취소
	public boolean setplike( int pNo, int mNo ) {
		
		String sql = "select * from plike where pNo = ? and mNo = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pNo);	ps.setInt(2, mNo);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				sql = "delete from plike where pNo = ? and mNo = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pNo);	ps.setInt(2, mNo);
				ps.executeUpdate();
				return false;
			}
			else {
				sql = "insert into plike( pNo, mNo) values( ?, ?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pNo);	ps.setInt(2, mNo);
				ps.executeUpdate();
				return true;
			}	
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	// 4. 현재 회원이 해당 제품 찜하기 상태 확인
	public boolean getplike( int pNo, int mNo ) {
		
		String sql = "select * from plike where pNo = ? and mNo = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pNo);	ps.setInt(2, mNo);
			rs = ps.executeQuery();
			
			if( rs.next() ) { return true; }
			
		} catch (Exception e) { System.out.println("예외 발생: " + e); }
		return false;
		
	}
	
}
