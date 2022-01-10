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
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {
	// 변수 설정
	private static BoardDao instance;
	private BoardDao() {
	}

	// getInstance 메소드
	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();
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

	// getTotalCnt 메소드 - 게시물 자료의 개수를 받는 메소드
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totCnt = 0;

		String sql = "select count(*) from board where re_level=0";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
				totCnt = rs.getInt(1);

			System.out.println("totCnt의 값은?? " + totCnt);

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

	// 게시물 list 메소드 - 게시물 DB를 불러오는 메소드
	public List<Board> list(int startRow, int endRow) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "Select * from (select rownum rn ,a.* from (select * from board where re_level=0) a) where rn between ? and ? order by uploaddate desc";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setImagepath(rs.getString("imagepath"));
				board.setViews(rs.getInt("views"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));

				list.add(board);
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

	
	// CommTotalCnt 메소드 - 댓글 자료의 개수를 받는 메소드
	public int CommTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totCnt = 0;

		String sql = "select count(*) from board where re_level >= 1";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
				totCnt = rs.getInt(1);

			System.out.println("totCnt의 값은?? " + totCnt);

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
	// Commlist 메소드 - 댓글 DB를 불러오는 메소드
	public List<Board> Commlist(int startRow, int endRow) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "Select * from (select rownum rn ,a.* from (select * from board where re_level >= 1) a) where rn between ? and ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setCommcontent(rs.getString("commcontent"));
				board.setCommdate(rs.getDate("commdate"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));
				board.setStdnum(rs.getInt("stdnum"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
			if (rs != null) rs.close();
		}
		return list;
	}
		
	// 관리자 페이지에서 게시물을 삭제하는 메소드
	public int boardDelete(int b_type, int b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql="delete from board where b_type=? and b_num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_type);
			pstmt.setInt(2, b_num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
		}
		return result;
	}

	   public int loginCheck(String id, String passwd) throws SQLException {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        String sql = "SELECT passwd from memberinfo where id=?";
	        String dbpasswd=null; 
	        int result=-1;

	        try {

	            conn = getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	               dbpasswd = rs.getString("passwd"); 
	                if (dbpasswd.equals(passwd)) 
	                    result = 1; 
	                else
	                    result = 0;
	            } else {
	                result = -1;
	            }

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } finally {
	          if(conn!=null)   conn.close();
	         if(pstmt!=null)   pstmt.close();
	         if(rs!=null)   rs.close();
	        }
	   return result;   
	   }
	   
	   
	   // ★★★★★ 게시물 검색 메소드 1 ★★★★★
		public List<Board> searchList(String keyWord, int startRow, int endRow) throws SQLException {
			List<Board> list = new ArrayList<Board>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "select * from (select rownum rn ,a.* from (select * from board where re_level=0 and (title like '%" + keyWord	+ "%' or content like '%" + keyWord + "%' or id like '%" + keyWord + "%') order by b_num desc) a ) where rn between ? and ?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Board board = new Board();
					
					board.setB_type(rs.getString("b_type"));
					board.setB_num(rs.getInt("b_num"));
					board.setId(rs.getString("id"));
					board.setUploaddate(rs.getDate("uploaddate"));
					board.setContent(rs.getString("content"));
					board.setTitle(rs.getString("title"));
					board.setImagepath(rs.getString("imagepath"));
					board.setViews(rs.getInt("views"));
					
					list.add(board);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			}
			return list;
		}
		// ★★★★★ 게시물 검색 메소드 2 ★★★★★
		public int getSearchCnt(String keyWord) throws SQLException {
			int totCnt = 0;
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			String sql = "SELECT COUNT(*) FROM BOARD WHERE re_level='0' and (title like '%" + keyWord
					+ "%' or content like '%" + keyWord + "%' or id like '%" + keyWord + "%')";
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

		
		// ★★★★★ 댓글 검색 메소드 1 ★★★★★
		public List<Board> searchCommList(String keyWord, int startRow, int endRow) throws SQLException {
		    
			List<Board> list=new ArrayList<Board>();
		      Connection conn=null;
		      PreparedStatement pstmt=null;
		      ResultSet rs=null;
		      
		      String sql = "select * from (select rownum rn ,a.* from" + "(select * from board where re_level>0 and (commcontent like '%"+keyWord+"%' or id like '%"+keyWord+"%')" + "order by b_num desc) a )" + "where rn between ? and ?";

				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						Board board = new Board();
						
						board.setB_type(rs.getString("b_type"));
						board.setB_num(rs.getInt("b_num"));
						board.setId(rs.getString("id"));
						board.setCommcontent(rs.getString("commcontent"));
						board.setCommdate(rs.getDate("commdate"));
						board.setRe_level(rs.getInt("re_level"));
						board.setRe_step(rs.getInt("re_step"));
						board.setRef(rs.getInt("ref"));
						board.setStdnum(rs.getInt("stdnum"));
						
						list.add(board);
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
		
		// ★★★★★ 댓글 검색 메소드 2 ★★★★★
		public int getCommSearchCnt(String keyWord) throws SQLException {
		      Connection conn=null;
		      Statement stmt=null;
		      ResultSet rs=null;
		      
		      int totCnt=0;
		   
		      String sql="SELECT COUNT(*) FROM BOARD where re_level>'0' and (commcontent like '%"+keyWord+"%' or id like '%"+keyWord+"%')";
				
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
