package dao.yr;

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


import dao.yr.Board;

public class MemberInfoDao {
	private static MemberInfoDao instance;

	private MemberInfoDao() {
	};

	public static MemberInfoDao getInstance() {
		if (instance == null) {
			instance = new MemberInfoDao();
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

	public void views(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update board set views=views+1 where b_num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}
	}

	public int getTotalCnt(String b_type) { // 토탈 카운트
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from board where ref=0 and b_type=? or ref is null and b_type=?";

		/*
		 * String sql = "select count(*) from board where id=? and ref=0 and b_type=?";
		 */ try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setString(2, b_type);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
			}
			System.out.println("BoardDao getTotalCnt tot->" + tot);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return tot;
	}

	public int testLogin(String id, String passwd) {
		int result = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id, passwd from memberinfo where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (passwd.equals(rs.getString("passwd"))) {
					result = 1;
				} else if (!(passwd.equals(rs.getString("passwd")))) {
					result = 0;
				}
			} else
				result = -1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Board> notiList(int startRow, int endRow, String b_type) throws SQLException {

		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from (select rownum rn , a.* from "
				+ " (select * from board where b_type=? and ref=0 or b_type=? and ref is null order by b_num) a ) where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setString(2, b_type);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setB_num(rs.getInt("b_num"));
				board.setB_type(rs.getString("b_type"));
				board.setId(rs.getString("id"));
				board.setUploadDate(rs.getDate("uploadDate"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setViews(rs.getInt("views"));
				board.setCommContent(rs.getString("commContent"));
				board.setCommDate(rs.getDate("commDate"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));
				list.add(board);
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

	public Board select(int num, String b_type) throws SQLException {
		Board board = new Board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from board where b_num=? and b_type=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, b_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board.setB_num(rs.getInt("b_num"));
				board.setB_type(rs.getString("b_type"));
				board.setId(rs.getString("id"));
				board.setUploadDate(rs.getDate("uploadDate"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setViews(rs.getInt("views"));
				board.setCommContent(rs.getString("commContent"));
				board.setCommDate(rs.getDate("commDate"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));
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
		return board;
	}

	public int notiWrite(int b_num, String id, String title, String content) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String b_type = "1"; // 게시글 번호, 아이디, 제목, 내용, 조회수, 등록일
		String sql = "insert into Board(b_type, b_num, id, title, content, views, uploadDate, ref, re_level, re_step) values(?,?,?,?,?,0,sysdate,0,0,0)";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setInt(2, b_num);
			pstmt.setString(3, id);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("notiWrite result::::" + result);
		return result;
	}

	public int getBnumMaxCnt(String b_type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select max(b_num) from board where b_type=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
			}
			System.out.println("BoardDao getTotalCnt tot->" + tot);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return tot;
	}

	public int notiUpdate(int b_num, String id, String title, String content) {

		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql = "update board set title= ?, content =? where b_type = '1' and b_num =? and id = ?";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, b_num);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (conn != null)
				try {
					conn.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	public List<Board> notiSelect(int b_num) {
		List<Board> list = new ArrayList<Board>();
		Board board = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String b_type = "1";
		String sql = "select * from board where b_num=? and b_type = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.setString(2, b_type);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (conn != null)
				try {
					conn.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}

	public int notiDelete(int b_num) {

		int result = 0;

		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql = "delete from board where b_num=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Board> searchList(String keyWord, int startRow, int endRow) {
		// TODO Auto-generated method stub
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 제목, 내용, 작성가 전부 포함이 된 레코드 전부 출력
		String sql = "select * from (select rownum rn ,a.* from"
				+ "(select * from board where re_level=0 and b_type='1' and " + "(title like '%" + keyWord
				+ "%' or content like '%" + keyWord + "%' or id like '%" + keyWord + "%') " + "order by b_num desc) a )"
				+ "where rn between ? and ?";
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
				board.setUploadDate(rs.getDate("uploadDate"));
				board.setTitle(rs.getString("title"));
				board.setViews(rs.getInt("views"));
				list.add(board);

				System.out.println(list.toString());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getSearchCnt(String keyWord) {
		int totCnt = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM BOARD WHERE B_TYPE='1' and re_level='0' and (title like '%" + keyWord
				+ "%' or content like '%" + keyWord + "%' or id like '%" + keyWord + "%')";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
				totCnt = rs.getInt(1);
			System.out.println("BoardDao getTotalCnt-> " + totCnt);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totCnt;
	}

	public int notiWriteComm(int maxNum, String id, String commContent, int b_num) {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into board(b_type, b_num, id, commContent, uploadDate, ref) values(1,?,?,?,sysdate,?)";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, id);
			pstmt.setString(3, commContent);
			pstmt.setInt(4, b_num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("notiWriteComm result::::" + result);

		return result;
	}

	public List<Board> commList(int b_num, String b_type) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where title is null and b_type=? and ref=? order by re_step, re_level";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setInt(2, b_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setB_type(rs.getString("b_type"));
				board.setCommContent(rs.getString("commContent"));
				board.setCommDate(rs.getDate("commDate"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int notiReWriteComm(int maxNum, String id, int b_num, String commContent) {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into board(b_type, b_num, id, commContent, uploadDate, ref, ref_level) values(1,?,?,?,sysdate,?,2)";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, id);
			pstmt.setString(3, commContent);
			pstmt.setInt(4, b_num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("notiWriteComm result::::" + result);

		return result;
	}

	public int notiCommWrite(String id, String b_type, int maxBnum, int b_num, String commContent) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board(b_type, b_num, id, commContent, commdate, re_level, re_step, ref) values(?,?,?,?,sysdate,1,?,?)";
		conn = getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setInt(2, maxBnum);
			pstmt.setString(3, id);
			pstmt.setString(4, commContent);
			pstmt.setInt(5, maxBnum);
			pstmt.setInt(6, b_num);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int notiRePlyWrite(String b_type, String id, int maxBnum, int ref, int re_step, int maxReLevel, String commContent) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="insert into board(b_type, b_num, id, commContent, commDate, re_level, re_step, ref) values(?,?,?,?,sysdate,?,?,?)";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setInt(2, maxBnum);
			pstmt.setString(3, id);
			pstmt.setString(4, commContent);
			pstmt.setInt(5, maxReLevel);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, ref);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int getRelevelMaxCnt(int ref, int re_step) {
		int maxReLevel = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select max(re_level) from board where ref=? and re_step=?";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_step);
			rs=pstmt.executeQuery();
			if(rs.next()) maxReLevel=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxReLevel;
	}

	public List<Board> getComment(String b_type,int b_num) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where b_type=? and b_num=?";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setInt(2, b_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setCommContent(rs.getString("commContent"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int notiCommUpdate(String b_type, int b_num, String commContent) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update board set commContent=?, commDate=sysdate where b_type=? and b_num=?";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, commContent);
			pstmt.setString(2, b_type);
			pstmt.setInt(3, b_num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int getRef(String b_type, int b_num) {
		int ref=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ref from board where b_type=? and b_num=?";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b_type);
			pstmt.setInt(2, b_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ref=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ref;
	}

	public int notiCommDelete(int b_num, String b_type) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from board where b_num=? and b_type=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.setString(2, b_type);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Board> notiSearch(String search, String b_type, int startRow, int endRow) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql="select * from (select rownum rn ,a.* from(select * from board where ref=0 and b_type="+b_type+" and (title like '%" + search + "%' or content like '%" + search + "%' or id like '%" + search + "%') order by b_num desc) a ) where rn between "+startRow+" and "+endRow+"";
		conn = getConnection();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Board board = new Board();
				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViews(rs.getInt("views"));
				board.setUploadDate(rs.getDate("uploadDate"));
				list.add(board);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
				try {
					if(conn!=null)	conn.close();
					if(stmt!=null)	stmt.close();
					if(rs!=null)	rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
}
