package dao.gm;

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

public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if(instance==null)
			instance=new BoardDao();
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int loginCheck(String id, String passwd) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT passwd FROM memberinfo WHERE id=?";
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
    		if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
        }
	return result;	
	}
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from board where b_type='6' and ref='0'";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) 
				tot=rs.getInt(1);
			System.out.println("BoardDao getTotalCnt tot->"+tot);
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return tot;
	}

	public List<Board> list(int startRow, int endRow) throws SQLException {
		List<Board> list=new ArrayList<Board>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println(startRow);
		System.out.println(endRow);
		String sql="Select * from (select rownum rn ,a.* from "
				+ "(select * from board where b_type='6' and ref='0' order by b_num desc) a) "
				+ "where rn between ? and ?";
		// board의 모든 행 중 commnum가 널이거나 b_type이 6인 것을 ref로 내림차순으로 지정한 것을 a로 명명
		// rownum을 rn으로 만들고, a에 대한 모든 행을 출력
		// 위 내용을 where 조건으로 ?와 ? 사이 값의 모든 행을 출력
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board=new Board();
				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setTitle(rs.getString("title"));
				board.setImagepath(rs.getString("imagepath"));
				board.setViews(rs.getInt("views"));
				board.setContent(rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return list;
	}
	
	public void readCount(int b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update board set views=views+1 where b_num=?";
	
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
	}
	
	public List<Board> select(int b_num, String pageNum, String b_type) throws SQLException {
		System.out.println("data params : "+b_num);
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;	
		Statement stmt= null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board where b_type=6 START WITH b_num="+b_num+" CONNECT BY PRIOR b_num = ref";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setViews(rs.getInt("views"));
				board.setUploaddate(rs.getDate("uploaddate"));
				
				board.setCommcontent(rs.getString("commcontent"));
				board.setCommdate(rs.getDate("commdate"));
				board.setRef(rs.getInt("ref"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setImagepath(rs.getString("imagepath"));
				list.add(board);
				System.out.println("collected list : "+ list);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	
	public int update(Board board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		String sql="update board set title=?, id=?, content=?, imagepath=? where b_type='6' and b_num=?";

		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getImagepath());
			pstmt.setInt(5, board.getB_num());
			result=pstmt.executeUpdate();
			System.out.println("boarddao update->"+board.getId());
			System.out.println("boardDao update->"+result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	
	public int delete(int b_num, String b_type) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
//		String dbID=null;
		int result=0;
		
		String sql="delete from board where b_type='6' and b_num in(select b_num from board start with b_num=? connect by prior b_num=ref)";
						// 계층형 삭제
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			result = pstmt.executeUpdate();
			System.out.println("collected list : "+ result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
//			if(rs!=null)	rs.close();
		}
		return result;
	}
	
	// 글쓰기 함수
	public int insert(Board board) throws SQLException {
		System.out.println("BoardDao_insert->"+board);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;						// 1,2,3,   4  ,5 ,6,7,8, 9  , 10,11,12,13,14
		String sql = "insert into board values(6,?,?,sysdate,?,?,?,0,null,null,0,0,?,null)";
		String sql1 = "select nvl(max(b_num),0) from board";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery(); 
			rs.next();					
			int number = rs.getInt(1)+1;	// 기존글+1하여 최신 b_num 만들기
			rs.close();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);	// 여기에 대입
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getTitle());
			pstmt.setString(5, board.getImagepath());
			pstmt.setInt(6, board.getB_num());	// ref - 댓글시에만 / b_num을 사용하지 않으므로 0으로 저장됨

			result=pstmt.executeUpdate();
			System.out.println("collected result : "+ result);
		} catch (SQLException e) {
			System.out.println("BoardDao_insert->"+e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	// 댓글 글쓰기
	public int comminsert(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into board(b_type, b_num, id, commcontent, commdate, ref, re_level, re_step)"
				   + " values('6', (select nvl(max(b_num),0)+1 from board where b_type='6'),?,?,sysdate,?,"
				   	// b_type = 6 / b_num = b_num의 최대값+1(b_type=6)
				   + "'1',(select nvl(max(re_step),0)+1 from board where ref=?)) ";
					// re_level - 1(댓글) / re_step = re_step의 최대값+1(ref와 b_num이 같은 것에 대해서만)
					// 대댓글 - > 메서드 새로 만들고 re_level=2로 주면 될 듯
		try {
			conn = getConnection();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getCommcontent());
			pstmt.setInt(3, board.getB_num());	// re_level
			pstmt.setInt(4, board.getB_num()); // ref - 댓글 = 글번호
			
			result=pstmt.executeUpdate();
			
			System.out.println("boarddao id - "+board.getId());
			System.out.println("boarddao content - "+board.getCommcontent());
			System.out.println("boarddao b_num - "+board.getB_num());
			
			System.out.println("comminsert board data->"+result);
		} catch (Exception e) {
			System.out.println("commInsert exception->"+e.getMessage());
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	// 댓글 삭제
	public int commDelete(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from board where b_type='6' and b_num in(select b_num from board start with b_num=? connect by prior b_num=ref)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_num());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("commDelete DAO exception->"+e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		}
		return result;
	}

 // 댓글 수정
	public int commupadate(String commb_num, String commcontent) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update board set commcontent=? where b_num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commcontent);
			pstmt.setString(2, commb_num);
			
			result = pstmt.executeUpdate(); 
			if(result == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			System.out.println("commselect exception->"+e.getMessage());
		} finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}

	public List<Board> searchList(String search, int startRow, int endRow) throws SQLException {
		List<Board> list=new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from"
	            + "(select * from board where re_level=0 and b_type='6' and "
	            + "(title like '%"+search+"%' or content like '%"+search+"%' or id like '%"+search+"%') "
	            + "order by b_num desc) a )"
	            + "where rn between ? and ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setB_type(rs.getString("b_type"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setViews(rs.getInt("views"));
				board.setImagepath(rs.getString("imagepath"));
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return list;
	}
	
	public int getSearchCnt(String search) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "SELECT COUNT(*) FROM BOARD WHERE B_TYPE='6' and re_level='0' and (title like '%"+search+"%' or content like '%"+search+"%' or id like '%"+search+"%')";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) tot=rs.getInt(1);
			System.out.println("BoardDao getTotalCnt tot->"+tot);
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return tot;
	}

	public List<Board> indexlist(int startRow, int endRow) throws SQLException {
		List<Board> list=new ArrayList<Board>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println(startRow);
		System.out.println(endRow);
		String sql="Select * from (select rownum rn ,a.* from "
				+ "(select * from board where b_type='6' and ref='0' order by views desc) a) "
				+ "where rn between ? and ?";
		// board의 모든 행 중 commnum가 널이거나 b_type이 6인 것을 ref로 내림차순으로 지정한 것을 a로 명명
		// rownum을 rn으로 만들고, a에 대한 모든 행을 출력
		// 위 내용을 where 조건으로 ?와 ? 사이 값의 모든 행을 출력
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board=new Board();
				board.setB_type(rs.getString("b_type"));
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setTitle(rs.getString("title"));
				board.setImagepath(rs.getString("imagepath"));
				board.setViews(rs.getInt("views"));
				board.setContent(rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return list;
	}
//	대댓글(답글)
	public int commWriteRe(String b_num, String commcontent_re, String id) {
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int result = 0;
		String sql = "INSERT INTO board(b_type, b_num, id, commcontent, commdate, ref, re_level, re_step)"
				+ "VALUES('6', (SELECT nvl(max(b_num),0)+1 FROM board WHERE b_type=6), ?, ?,"
				+ "sysdate, ?, 2, (SELECT nvl(max(re_step),0)+1 FROM board WHERE ref=?))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, commcontent_re);
			pstmt.setString(3, b_num);
			pstmt.setString(4, b_num);
			result = pstmt.executeUpdate();
			
			System.out.println("dao->"+commcontent_re);
			if (result==1) {
				System.out.println("성공");
				conn.commit();
			} else {
				System.out.println("실패");
				conn.rollback();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("commWriteRe->"+e.getMessage());
		}finally {
			
				try {
					if (pstmt != null) pstmt.close();
					if (conn !=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			
		}
		return result; 
	}
	
}
