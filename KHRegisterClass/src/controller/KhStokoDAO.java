package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.KhStokoVO;


public class KhStokoDAO {
	// 디생
	// 멤버변수
	private String selectSQL = "SELECT * FROM KHSTOKO order by STOCK_NO ASC";
	private String selectByNumSQL = "SELECT * FROM KHSTOKO where STOCK_NO = ?";
	private String selectByNameSQL = "SELECT * FROM KHSTOKO where SUB_CATEGORY = ?";
	private String insertSQL = "INSERT INTO KHSTOKO VALUES (KHSTOKO_SEQ.nextval, ?, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'))";
	private String updateSQL = "UPDATE KHSTOKO SET CATEGORY = ?, SUB_CATEGORY = ?, PRICE = ?, STOCK = ?, EXP_DATE = TO_DATE(?, 'YYYY/MM/DD') WHERE STOCK_NO = ?";
	private String deleteSQL = "DELETE FROM KHSTOKO WHERE STOCK_NO = ?";

	// 제품 목록
	public ArrayList<KhStokoVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<KhStokoVO> khStokoList = new ArrayList<KhStokoVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int stockNo = rs.getInt("STOCK_NO");
				String category = rs.getString("CATEGORY");
				String subCategory = rs.getString("SUB_CATEGORY");
                int price = rs.getInt("PRICE");
                int stock = rs.getInt("STOCK");
                String expDate = rs.getString("EXP_DATE");
				KhStokoVO khStokoVO = new KhStokoVO(stockNo, category, subCategory, price, stock, expDate);
				khStokoList.add(khStokoVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return khStokoList;
	}

	// 제품번호검색
	public KhStokoVO selectByNum(KhStokoVO khStokoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		KhStokoVO _khStokoVO = null;  
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByNumSQL);
			pstmt.setInt(1, khStokoVO.getStockNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int stockNo = rs.getInt("STOCK_NO");
				String category = rs.getString("CATEGORY");
				String subCategory = rs.getString("SUB_CATEGORY");
                int price = rs.getInt("PRICE");
                int stock = rs.getInt("STOCK");
                String expDate = rs.getString("EXP_DATE");
				_khStokoVO = new KhStokoVO(stockNo, category, subCategory, price, stock, expDate);
			} 
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _khStokoVO;
	}

	// 제품명검색
	public KhStokoVO selectByCategory(String subCategoryCus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		KhStokoVO _khStokoVO = null;  
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByNameSQL);
			pstmt.setString(1, subCategoryCus);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int stockNo = rs.getInt("STOCK_NO");
				String category = rs.getString("CATEGORY");
				String subCategory = rs.getString("SUB_CATEGORY");
                int price = rs.getInt("PRICE");
                int stock = rs.getInt("STOCK");
                String expDate = rs.getString("EXP_DATE");
				_khStokoVO = new KhStokoVO(stockNo, category, subCategory, price, stock, expDate);
			} 
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _khStokoVO;
	}


	// 제품입력
	public int insert(KhStokoVO khStokoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1,khStokoVO.getCategory());
			pstmt.setString(2,khStokoVO.getSubCategory());
			pstmt.setInt(3,khStokoVO.getPrice());
			pstmt.setInt(4,khStokoVO.getStock());
			pstmt.setString(5,khStokoVO.getExpDate());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 제품수정
	public int update(KhStokoVO khStokoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1,khStokoVO.getCategory());
			pstmt.setString(2,khStokoVO.getSubCategory());
			pstmt.setInt(3,khStokoVO.getPrice());
			pstmt.setInt(4,khStokoVO.getStock());
			pstmt.setString(5,khStokoVO.getExpDate());
			pstmt.setInt(6, khStokoVO.getStockNo());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 제품삭제
	public int deleteByNum(KhStokoVO khStokoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, khStokoVO.getStockNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

// 재고 차감
public void updateStock(String category, String subCategory, int decreaseAmount) throws Exception {
    Connection con = null;
    PreparedStatement pstmt = null;

    try {
        con = DBUtil.getConnection();
        String sql = "UPDATE KHSTOKO SET STOCK = STOCK - ? WHERE CATEGORY = ? AND SUB_CATEGORY = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, decreaseAmount);
        pstmt.setString(2, category);
        pstmt.setString(3, subCategory);
        pstmt.executeUpdate();
    } finally {
        DBUtil.dbClose(con, pstmt, null);
    }
}

// 특정 품목 재고 차감
public int updateStockMinus(String category, String subCategory, int qty) {
    Connection con = null;
    PreparedStatement pstmt = null;
    int result = 0;

    String sql = "UPDATE KH_STOKO SET STOCK = STOCK - ? WHERE CATEGORY = ? AND SUB_CATEGORY = ?";

    try {
        con = DBUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, qty);            // 줄일 수량
        pstmt.setString(2, category);    // 대분류
        pstmt.setString(3, subCategory); // 소분류

        result = pstmt.executeUpdate();  // 업데이트 성공시 1 이상 반환

    } catch (SQLException e) {
        System.out.println("updateStockMinus 오류: " + e.getMessage());
    } finally {
        DBUtil.dbClose(con, pstmt, null);
    }

    return result;
}


}
