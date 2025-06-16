package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.KhStokoCusVO;


public class KhStokoCusDAO {
	// 디생
	// 멤버변수
	private String selectSQL = "SELECT * FROM KHSTOKO_CUS order by REC_NO ASC";
	private String selectByNumSQL = "SELECT * FROM KHSTOKO_CUS where REC_NO = ?";
	private String selectByNameSQL = "SELECT *  FROM KHSTOKO_CUS where SUB_CATEGORY_CUS = ?";
	private String insertSQL = "INSERT INTO KHSTOKO_CUS VALUES (KHSTOKO_CUS_SEQ.nextval, ?, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'))";
	private String updateSQL = "update KHSTOKO_CUS set CATEGORY_CUS = ?, SUB_CATEGORY_CUS = ?, PRICE_CUS = ?, STOCK_CUS = ?, EXP_DATE = TO_DATE(?, 'YYYY/MM/DD') where REC_NO = ?";
	private String deleteSQL = "DELETE FROM KHSTOKO_CUS WHERE REC_NO = ?";

	// 영수증 목록
	public ArrayList<KhStokoCusVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<KhStokoCusVO> khStokoCusList = new ArrayList<KhStokoCusVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int recNo = rs.getInt("REC_NO");
				String categoryCus = rs.getString("CATEGORY_CUS");
				String subCategoryCus = rs.getString("SUB_CATEGORY_CUS");
				int priceCus = rs.getInt("PRICE_CUS");
				int stockCus = rs.getInt("STOCK_CUS");
				String expDate = rs.getString("EXP_DATE");

				KhStokoCusVO khStokoCusVO = new KhStokoCusVO(recNo, categoryCus, subCategoryCus, priceCus, stockCus, expDate);
				khStokoCusList.add(khStokoCusVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return khStokoCusList;
	}

	// 영수증 번호검색
	public KhStokoCusVO selectByNum(KhStokoCusVO khStokoCusVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		KhStokoCusVO _khStokoCusVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByNumSQL);
			pstmt.setInt(1, khStokoCusVO.getRecNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int recNo = rs.getInt("REC_NO");
				String categoryCus = rs.getString("CATEGORY_CUS");
				String subCategoryCus = rs.getString("SUB_CATEGORY_CUS");
				int priceCus = rs.getInt("PRICE_CUS");
				int stockCus = rs.getInt("STOCK_CUS");
				String expDate = rs.getString("EXP_DATE");
				_khStokoCusVO = new KhStokoCusVO(recNo, categoryCus, subCategoryCus, priceCus, stockCus, expDate);
			} 
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _khStokoCusVO;
	}

	// 영수증이름검색
	public KhStokoCusVO selectByName(KhStokoCusVO khStokoCusVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		KhStokoCusVO _khStokoCusVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByNameSQL);
			pstmt.setString(1, khStokoCusVO.getSubCategoryCus());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int recNo = rs.getInt("REC_NO");
				String categoryCus = rs.getString("CATEGORY_CUS");
				String subCategoryCus = rs.getString("SUB_CATEGORY_CUS");
				int priceCus = rs.getInt("PRICE_CUS");
				int stockCus = rs.getInt("STOCK_CUS");
				String expDate = rs.getString("EXP_DATE");
				_khStokoCusVO = new KhStokoCusVO(recNo, categoryCus, subCategoryCus, priceCus, stockCus, expDate);
			} 
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _khStokoCusVO;
	}


	// 영수증 입력
	public int insert(KhStokoCusVO khStokoCusVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		// private String insertSQL = "INSERT INTO KHSTOKO_CUS VALUES (KHSTOKO_CUS_SEQ.nextval, ?, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'))";
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			// java.sql.Date sqlDate = java.sql.Date.valueOf(khStokoCusVO.getExpDate());
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1,khStokoCusVO.getCategoryCus());
			pstmt.setString(2,khStokoCusVO.getSubCategoryCus());
			pstmt.setInt(3,khStokoCusVO.getPriceCus());
			pstmt.setInt(4,khStokoCusVO.getStockCus());
			pstmt.setString(5, khStokoCusVO.getExpDate());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생" + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 영수증 수정
	public int update(KhStokoCusVO khStokoCusVO) {
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
			pstmt.setString(1,khStokoCusVO.getCategoryCus());
			pstmt.setString(2,khStokoCusVO.getSubCategoryCus());
			pstmt.setInt(3,khStokoCusVO.getPriceCus());
			pstmt.setInt(4,khStokoCusVO.getStockCus());
			pstmt.setString(5,khStokoCusVO.getExpDate());
			pstmt.setInt(6, khStokoCusVO.getRecNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 영수증 삭제
	public int deleteByNum(KhStokoCusVO khStokoCusVO) {
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
			pstmt.setInt(1, khStokoCusVO.getRecNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 영수증 번호로 검색
	public KhStokoCusVO selectByRecNo(int recNo) {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    KhStokoCusVO vo = null;

    String sql = "SELECT * FROM KHSTOKO_CUS WHERE REC_NO = ?";

    try {
        con = DBUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, recNo);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            vo = new KhStokoCusVO(
                rs.getInt("REC_NO"),
                rs.getString("CATEGORY_CUS"),
                rs.getString("SUBCATEGORY_CUS"),
                rs.getInt("PRICE_CUS"),
                rs.getInt("STOCK_CUS"),
                rs.getString("EXP_DATE")
            );
        }

    } catch (SQLException e) {
        System.out.println("selectByRecNo 오류 발생");
        e.printStackTrace();
    } finally {
        DBUtil.dbClose(con, pstmt, rs);
    }

    return vo;
}


}
