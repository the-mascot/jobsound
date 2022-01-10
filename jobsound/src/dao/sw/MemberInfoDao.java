package dao.sw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberInfoDao {
	// 변수 설정
	private static MemberInfoDao instance;

	private MemberInfoDao() {
	}

	// getInstance 메소드
	public static MemberInfoDao getInstance() {
		if (instance == null)
			instance = new MemberInfoDao();
		return instance;
	}

	// getConnection 메소드
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// MemberInfo 리스트로 보기
	public List<MemberInfo> list(int startRow, int endRow) throws SQLException {
		List<MemberInfo> list = new ArrayList<MemberInfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "Select * from (select rownum rn ,a.* from (select * from MemberInfo) a) where rn between ? and ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberInfo mi = new MemberInfo();

				mi.setId(rs.getString("id"));
				mi.setName(rs.getString("name"));
				mi.setNickname(rs.getString("nickname"));
				mi.setPasswd(rs.getString("passwd"));
				mi.setGender(rs.getString("gender"));
				mi.setTel(rs.getString("tel"));
				mi.setEmail(rs.getString("email"));
				mi.setBirth(rs.getString("birth"));
				mi.setAddr(rs.getString("addr"));
				mi.setReg_chk(rs.getString("reg_chk"));
				mi.setWidraw(rs.getString("widraw"));
				mi.setRegdate(rs.getDate("RegDate"));

				list.add(mi);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	// getTotalCnt 메소드
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totCnt = 0;
		String sql = "select count(*) from MemberInfo";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				totCnt = rs.getInt(1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return totCnt;
	}

	// memberchk 메소드 : 아이디를 가지고, 자세한 회원 정보를 조회하는 기능
	public MemberInfo memberchk(String id) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		MemberInfo mi = new MemberInfo();

		String sql = "select * from memberinfo where id='"+id+"'";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				mi.setId(rs.getString("id"));
				mi.setName(rs.getString("name"));
				mi.setNickname(rs.getString("nickname"));
				mi.setPasswd(rs.getString("passwd"));
				mi.setGender(rs.getString("gender"));
				mi.setTel(rs.getString("tel"));
				mi.setEmail(rs.getString("email"));
				mi.setBirth(rs.getString("birth"));
				mi.setAddr(rs.getString("addr"));
				mi.setReg_chk(rs.getString("reg_chk"));
				mi.setWidraw(rs.getString("widraw"));
				mi.setRegdate(rs.getDate("regdate"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return mi;
	}
	
	// ★★★★★ 회원 검색 메소드 1 ★★★★★
	public List<MemberInfo> searchList(String keyWord, int startRow, int endRow) throws SQLException {

		List<MemberInfo> list = new ArrayList<MemberInfo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from (select rownum rn ,a.* from (select * from memberinfo where name like '%" + keyWord + "%' or id like '%" + keyWord + "%') a) where rn between ? and ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberInfo memberinfo = new MemberInfo();
				
				memberinfo.setId(rs.getString("id"));
				memberinfo.setName(rs.getString("name"));
				memberinfo.setNickname(rs.getString("nickname"));
				memberinfo.setGender(rs.getString("gender"));
				memberinfo.setTel(rs.getString("tel"));
	            memberinfo.setEmail(rs.getString("email"));
				memberinfo.setBirth(rs.getString("birth"));
				
				memberinfo.setAddr(rs.getString("addr"));
				memberinfo.setReg_chk(rs.getString("reg_chk"));
				memberinfo.setWidraw(rs.getString("widraw"));
				memberinfo.setRegdate(rs.getDate("regdate"));

				list.add(memberinfo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
			if (rs != null) rs.close();
		}
		return list;
	}
	
	
	// ★★★★★ 회원 검색 메소드 2 ★★★★★
	public int getSearchCnt(String keyWord) throws SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totCnt = 0;

		String sql = "select count(*) from memberinfo where name like '%" + keyWord + "%' or id like '%" + keyWord + "%'";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
				totCnt = rs.getInt(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		}
		return totCnt;
	}
	
	
}
