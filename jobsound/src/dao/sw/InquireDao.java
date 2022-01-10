package dao.sw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InquireDao {
	
	// 변수 설정
	private static InquireDao instance;
	private InquireDao() {
	}

	// getInstance 메소드
	public static InquireDao getInstance() {
		if (instance == null)
			instance = new InquireDao();
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
	
	// InqTotalCnt 메소드 - 문의사항 자료의 개수를 받는 메소드
	public int InqTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totCnt = 0;

		String sql = "select count(*) from inquire";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
				totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}
		return totCnt;
	}

	// Inqlist 메소드 - 문의사항 DB를 불러오는 메소드
	public List<Inquire> Inqlist(int startRow, int endRow) throws SQLException {
		List<Inquire> list = new ArrayList<Inquire>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "Select * from (select rownum rn ,a.* from (select * from inquire) a) where rn between ? and ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Inquire inquire = new Inquire();

				inquire.setInqnum(rs.getInt("inqnum"));
				inquire.setId(rs.getString("id"));
				inquire.setInqtitle(rs.getString("inqtitle"));
				inquire.setInqcontent(rs.getString("inqcontent"));
				inquire.setAskcontent(rs.getString("askcontent"));
				inquire.setWritedate(rs.getDate("writedate"));
				inquire.setAskdate(rs.getDate("askdate"));

				list.add(inquire);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		}
		return list;
	}
	
	// inqboard 메소드 - 특정 문의사항을 눌렀을 때, 그 번호에 해당하는 문의사항을 출력하는 메소드 
	public Inquire inqboard(int inqnum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Inquire inquire = new Inquire();
		
		String sql = "select * from inquire where inqnum=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inqnum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			inquire.setInqnum(rs.getInt("inqnum"));
			inquire.setId(rs.getString("id"));
			inquire.setInqtitle(rs.getString("inqtitle"));
			inquire.setInqcontent(rs.getString("inqcontent"));
			inquire.setAskcontent(rs.getString("askcontent"));
			inquire.setWritedate(rs.getDate("writedate"));
			inquire.setAskdate(rs.getDate("askdate"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
			if (rs != null) rs.close();
		}
		return inquire;
	}
	
	// 문의사항 답변하는 메소드
	public int Inqupdate(String inqnum, String content) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update inquire set askcontent=?, askdate=sysdate where inqnum=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, content);
			pstmt.setString(2, inqnum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
		}
		return result;
	}
	
	// ★★★★★ 문의 사항 검색 메소드 1 ★★★★★
	public List<Inquire> searchList(String keyWord, int startRow, int endRow) throws SQLException {
		
	      List<Inquire> list=new ArrayList<Inquire>();
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      
	      String sql = "select * from (select rownum rn ,a.* from (select * from inquire where (id like '%"+keyWord+"%' or inqtitle like '%"+keyWord+"%' or inqcontent like '%"+keyWord+"%')) a) where rn between ? and ?";
	      
	      try {
	         conn=getConnection();
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, endRow);
	         rs=pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	Inquire inquire=new Inquire();
	            
				inquire.setInqnum(rs.getInt("inqnum"));
				inquire.setId(rs.getString("id"));
				inquire.setInqtitle(rs.getString("inqtitle"));
				inquire.setInqcontent(rs.getString("inqcontent"));
				inquire.setAskcontent(rs.getString("askcontent"));
				inquire.setWritedate(rs.getDate("writedate"));
				inquire.setAskdate(rs.getDate("askdate"));
	            
	            list.add(inquire);
	         }
	      } catch (SQLException e) {
	         System.out.println(e.getMessage());
	         e.printStackTrace();
	      } finally {
	               if(conn!=null) conn.close();
	               if(pstmt!=null) pstmt.close();
	               if(rs!=null) rs.close();
	      }
	      return list;
	   }
	
	
	// ★★★★★ 문의 사항 검색 메소드 2 ★★★★★
	public int getSearchCnt(String keyWord) throws SQLException {
	      Connection conn=null;
	      Statement stmt=null;
	      ResultSet rs=null;
	      int totCnt=0;
	   
	      String sql="select COUNT(*) FROM inquire where (id like '%" + keyWord + "%' or inqtitle like '%" + keyWord + "%' or inqcontent like '%" + keyWord + "%')";
	     
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
