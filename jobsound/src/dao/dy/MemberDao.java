package dao.dy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {

	private static MemberDao instance;

	private MemberDao() {
	}

	public static MemberDao getInstance() { // 싱글턴
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

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

	public int loginCheck(String id, String passwd) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbPW = "";
		int result = -1;

		try {
			String sql = "SELECT passwd FROM memberinfo WHERE id=?";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbPW = rs.getString("passwd");

				if (dbPW.equals(passwd))
					result = 1;
				else
					result = 0;

			} else {
				result = -1;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return result;
	}

	public String acntidCheck(String name, String email) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT id FROM memberinfo WHERE name=? and email=?"; // id를 보내줄거니까 id셀렉
		String id = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name); // 1의 의미는 첫번째 '?' 값을 찾아줌!
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("id");
				System.out.println("id의 값은? " + id);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return id;
	}

	public String acntPwCheck(String id, String name, String email) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT passwd FROM memberinfo WHERE id=? and name=? and email=?"; // id를 보내줄거니까 id셀렉
		String passwd = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // 1의 의미는 첫번째 '?' 값을 찾아줌!
			pstmt.setString(2, name); 
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				passwd = rs.getString("passwd");
				System.out.println("비밀번호는 ? " + passwd);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return passwd;
	}
	
	
	public int joinUpdate(MemberInfo mi) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO memberinfo VALUES (?,?,?,?,?,?,?,?,null,'1','0',sysdate)";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mi.getId());
			pstmt.setString(2, mi.getName());
			pstmt.setString(3, mi.getNickname());
			pstmt.setString(4, mi.getPasswd());
			pstmt.setString(5, mi.getGender());
			pstmt.setString(6, mi.getTel());
			pstmt.setString(7, mi.getEmail());
			pstmt.setString(8, mi.getBirth());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;	
		
	}//joinUpdate문
	
}//멤버다오	
	
	