package dao.di;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {};
	
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	private Connection getConnection() {
		Connection conn=null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int getTotalCnt(String id, String b_type) {
		int tot=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql="select count(*) from board where id=? and ref=0 and b_type=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, b_type);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				tot=rs.getInt(1);
				};
			System.out.println("tot:::::::::::"+tot);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return tot;
	}

	public List<Board> mypageBoardList(int startRow, int endRow, String id, String b_type) {
		List<Board> list = new ArrayList<Board>();
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from(select rownum rn, a.* from (select * from board where id=? and b_type=? and ref=0 order by b_num desc) a) where rn between ? and ?";
		// ref is null로 댓글이 아닌 것만 추려냄
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, b_type);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setB_type(rs.getString("b_type"));// 글유형
				board.setB_num(rs.getInt("b_num"));// 글번호
				board.setTitle(rs.getString("title"));// 글제목
				board.setRe_step(rs.getInt("re_step"));// 댓글수(re_step)
				board.setUploadDate(rs.getDate("uploadDate"));// 작성일
				board.setViews(rs.getInt("views"));// 조회수
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public int myBoardDelete(int[] bNums) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from board where b_num=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for (int i: bNums) {
				pstmt.setInt(1, i);
				result=pstmt.executeUpdate();
			}
			System.out.println("refDelete result::::"+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}
	public void myBoardRefDelete(int[] bNums) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from board where ref=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for (int i: bNums) {
				pstmt.setInt(1, i);
				result=pstmt.executeUpdate();
			}
			System.out.println("refDelete result::::"+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public int getCmtTotalCnt(String id) {
		int cmtTotCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from board where id=? and commcontent is not null";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cmtTotCnt=rs.getInt(1);
			}
			System.out.println("getCmtTotalCnt ::::::"+cmtTotCnt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cmtTotCnt;
	}

	public List<Board> myCommentList(int startRow, int endRow, String id) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from(select rownum rn, a.* from (select * from board where id=? and commcontent is not null order by b_num desc) a) where rn between ? and ?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Board board = new Board();
				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setCommContent(rs.getString("commContent"));
				board.setCommDate(rs.getDate("commDate"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Board> mypageStdChkList(int startRow, int endRow, String id, String b_type) {
		List<Board> list = new ArrayList<Board>();
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from(select rownum rn, a.* from (select * from board where id=? and b_type=? and ref=0 order by b_num desc) a) where rn between ? and ?";
		// ref is null로 댓글이 아닌 것만 추려냄
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, b_type);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setUploadDate(rs.getDate("uploadDate"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setImagePath(rs.getString("imagePath"));
				board.setViews(rs.getInt("views"));
				board.setStdNum(rs.getInt("stdNum"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

}
