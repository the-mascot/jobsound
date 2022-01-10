package dao.sh;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;

import javax.naming.*;

public class BoardDao {
	
	private static BoardDao instance;
	
	private BoardDao() {}
	
	public static BoardDao getInstance() { // 싱글턴
		if (instance == null) {	
			instance = new BoardDao();		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
		}
		return conn;
	}
	
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;	
		Statement stmt= null; 
		ResultSet rs = null;    
		int tot = 0;
		String sql = "select count(*) from board where b_type='3' and re_level=0";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot = rs.getInt(1);
			System.out.println("BoardDao getTotalCnt tot->"+tot);
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	
	public List<Board> comuList(int startRow, int endRow) throws SQLException	{
		List<Board> list = new ArrayList<Board>();		
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn ,a.* from "
				+ "(select * from board where re_level=0 and b_type='3' order by b_num desc) a ) "
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
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setTitle(rs.getString("title"));
				board.setViews(rs.getString("views"));
				board.setImagepath(rs.getString("imagepath"));
				list.add(board);
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 		
		return list;
	}
	
	public void readCount(String b_num) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		String sql="update board set views=views+1 where b_num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_num);			
			pstmt.executeUpdate();
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
	}	
	
	public Board comuContentRead(String b_num) throws SQLException {
		Board board = new Board();	
		Connection conn = null;	
		Statement stmt= null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board where b_num="+b_num+" and b_type=3";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				board.setB_num(rs.getInt("b_num"));
				board.setB_type(rs.getString("b_type"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setViews(rs.getString("views"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setRef(rs.getInt("ref"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setImagepath(rs.getString("imagepath"));
			}
		} catch(Exception e) {
			System.out.println("comuContentRead 에러");
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return board;
	}

	public List<Comment> comuCommRead(String b_num) throws SQLException {
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();		
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "SELECT id, b_type, b_num, commcontent, commdate, ref, re_level, re_step "
				+ "FROM board where b_type=3 START WITH ref=?"
				+ "CONNECT BY PRIOR b_num = ref";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment comm = new Comment();
				comm.setId(rs.getString("id"));
				comm.setB_type(rs.getString("b_type"));
				comm.setB_num(rs.getInt("b_num"));
				comm.setCommcontent(rs.getString("commcontent"));
				comm.setCommdate(rs.getDate("commdate"));
				comm.setRef(rs.getInt("ref"));
				comm.setRe_level(rs.getInt("re_level"));
				comm.setRe_step(rs.getInt("re_step"));
				list.add(comm);
			}
		} catch(Exception e) {	
			System.out.println("comucommread 에러");
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}

	public int addComm(String b_num, String commcontent, String id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = 0;
		System.out.println("넘어온 b_num"+b_num);
		String sql = "INSERT INTO board(b_type, b_num, id, commcontent, commdate, ref, re_level, re_step)"
				+ "VALUES('3', (SELECT nvl(max(b_num), 0)+1 FROM board WHERE b_type=3), ?, ?,"
				+ "sysdate, ?, '1',"
				+ "(SELECT nvl(max(re_step), 0)+1 FROM board WHERE ref=?))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, commcontent);
			pstmt.setString(3, b_num);
			pstmt.setString(4, b_num);
			result = pstmt.executeUpdate();
			
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("addComm 에러");
			System.out.println(e.getMessage());
		}finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	public int updateComm(String b_num, String commcontent) {
		// TODO Auto-generated method stub
		
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = -1;
		
		String sql = "UPDATE board SET commcontent=? where b_num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commcontent);
			pstmt.setString(2, b_num);
			
			result = pstmt.executeUpdate();
			
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("실패");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn !=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return result;
	}

	public int comuWrite(String id, String content, String title, String imagepath) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = -1;
		
		String sql = "INSERT INTO board(b_type, b_num, id, uploaddate, content, title,"
				+ "imagepath, views , re_level, re_step, ref)"
				+ "VALUES(3, (SELECT max(b_num)+1 FROM board WHERE b_type=3),"
				+ "?, sysdate, ?, ?, ?, 0, 0, 0, 0)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, content);
			pstmt.setString(3, title);
			pstmt.setString(4, imagepath);
			
			result = pstmt.executeUpdate();
			
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("실패");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn !=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		return result;
	}

	public int addCommRe(String b_num, String commcontent, String id) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = 0;
		String sql = "INSERT INTO board(b_type, b_num, id, commcontent, commdate, ref, re_level, re_step)"
				+ "VALUES('3', (SELECT nvl(max(b_num),0)+1 FROM board WHERE b_type=3), ?, ?,"
				+ "sysdate, ?, 2,"
				+ "(SELECT nvl(max(re_step),0)+1 FROM board WHERE ref=? and re_level=2))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, commcontent);
			pstmt.setString(3, b_num);
			pstmt.setString(4, b_num);
			result = pstmt.executeUpdate();
			
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			
				try {
					if (pstmt != null) pstmt.close();
					if (conn !=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			
		}
		return result;
	}

	public int comuBoardDel(String b_num) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = -1;
		
		String sql = "DELETE FROM board WHERE b_num in (SELECT b_num "
				+ "FROM board START WITH b_num=?"
				+ "CONNECT BY PRIOR b_num = ref) and b_type=3";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_num);
			result = pstmt.executeUpdate();
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				result = 0;
			}
		}
		return result;
	}

	public boolean chkWriter(String b_num, String id) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String chkId = "";
		boolean result = false;
		System.out.println("chk b_num"+b_num);
		
		String sql = "SELECT id FROM board WHERE b_num=? and b_type=3";
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				chkId = rs.getString("id");
				System.out.println("chkID는?"+chkId);
				System.out.println("로그인중인 아이디는?"+id);
			}
			
			if (id.equals(chkId)) {
				System.out.println("true 리턴");
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Board boardUpdateForm(String b_num) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		Board board = null;
		String imagepath = ""; // imagepath 가공용
		
		String sql = "SELECT * FROM board where b_num="+b_num+" and b_type=3";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				board = new Board();
				board.setB_num(rs.getInt("b_num"));
				System.out.println("board에 담긴 b_num값"+board.getB_num());
				board.setB_type(rs.getString("b_type"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				imagepath = rs.getString("imagepath"); 
				String str= imagepath.substring(imagepath.lastIndexOf("\\")+1);
				board.setImagepath(str);
			}
		} catch(Exception e) {
			System.out.println("comuContentRead 에러");
			System.out.println(e.getMessage()); 
		} finally {
				try {
					if (rs !=null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn !=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return board;
	}

	public int boardUpdate(String b_num, String content, String title, String imagepath) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = 0;
		
		String sql = "UPDATE board SET content=?,title=?,imagepath=? WHERE b_num=? AND b_type=3";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, title);
			pstmt.setString(3, imagepath);
			pstmt.setString(4, b_num);
			
			result = pstmt.executeUpdate();
			
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("실패");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn !=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	public int getSearchCnt(String keyWord) {
		int totCnt=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
	
		String sql="SELECT COUNT(*) FROM BOARD WHERE B_TYPE='3' and re_level='0' and (title like '%"+keyWord+"%' or content like '%"+keyWord+"%' or id like '%"+keyWord+"%')";
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next())
				totCnt=rs.getInt(1);
			System.out.println("BoardDao getTotalCnt-> "+totCnt);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(conn!=null) conn.close();
					if(stmt!=null) stmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return totCnt;
	}

	public List<Board> searchList(String keyWord, int startRow, int endRow) {
		// TODO Auto-generated method stub
		List<Board> list=new ArrayList<Board>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql = "select * from (select rownum rn ,a.* from"
				+ "(select * from board where re_level=0 and b_type='3' and "
				+ "(title like '%"+keyWord+"%' or content like '%"+keyWord+"%' or id like '%"+keyWord+"%') "
				+ "order by b_num desc) a )"
				+ "where rn between ? and ?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			System.out.println("검색 진입");
			while(rs.next()) {
				Board board=new Board();
				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setTitle(rs.getString("title"));
				board.setViews(rs.getString("views"));
				board.setImagepath(rs.getString("imagepath"));
				list.add(board);
				System.out.println("도는중");
				System.out.println(list.toString());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null)	pstmt.close();
					if(rs!=null)	rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}

	public int gettotSearchCnt(String keyWord) {
		int totCnt=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
	
		String sql="SELECT COUNT(*) FROM BOARD WHERE re_level='0' and (title like '%"+keyWord+"%' or content like '%"+keyWord+"%' or id like '%"+keyWord+"%')";
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next())
				totCnt=rs.getInt(1);
			System.out.println("BoardDao getTotalCnt-> "+totCnt);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(conn!=null) conn.close();
					if(stmt!=null) stmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return totCnt;
	}

	public List<Board> totSearchList(String keyWord, int startRow, int endRow) {
		// TODO Auto-generated method stub
				List<Board> list=new ArrayList<Board>();
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				
				String sql = "select * from (select rownum rn ,a.* from"
						+ "(select * from board where re_level=0 and "
						+ "(title like '%"+keyWord+"%' or content like '%"+keyWord+"%' or id like '%"+keyWord+"%') "
						+ "order by b_num desc) a )"
						+ "where rn between ? and ?";
				try {
					conn=getConnection();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					System.out.println("검색 실행");
					rs=pstmt.executeQuery();
					System.out.println("검색 진입");
					while(rs.next()) {
						Board board=new Board();
						board.setB_type(rs.getString("b_type"));
						board.setB_num(rs.getInt("b_num"));
						board.setId(rs.getString("id"));
						board.setContent(rs.getString("content"));
						board.setUploaddate(rs.getDate("uploaddate"));
						board.setTitle(rs.getString("title"));
						board.setViews(rs.getString("views"));
						board.setImagepath(rs.getString("imagepath"));
						list.add(board);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} finally {
						try {
							if(conn!=null) conn.close();
							if(pstmt!=null)	pstmt.close();
							if(rs!=null)	rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				return list;
	}
	
}