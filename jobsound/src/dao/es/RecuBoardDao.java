package dao.es;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class RecuBoardDao {
	private static RecuBoardDao instance;
	private RecuBoardDao() {}
	
	public static RecuBoardDao getInstance() {
		if(instance == null)
			instance = new RecuBoardDao();
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
	
	public MemberInfo selectMemberInfo(String id) throws SQLException {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberInfo mi=new MemberInfo();
        String sql="select * from MEMBERINFO where id=?";
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
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
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
    		if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
        }      
        return mi;
	}
	
	public int getTotalCnt() throws SQLException {
		int totCnt=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
	
		String sql="SELECT COUNT(*) FROM BOARD WHERE B_TYPE='2' and re_level='0'";
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
			if(conn!=null)	conn.close();
			if(stmt!=null)	stmt.close();
			if(rs!=null)	rs.close();
		}
		return totCnt;
	}
	
	public int getSearchCnt(String keyWord) throws SQLException {
		int totCnt=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
	
		String sql="SELECT COUNT(*) FROM BOARD WHERE B_TYPE='2' and re_level='0' and (title like '%"+keyWord+"%' or content like '%"+keyWord+"%' or id like '%"+keyWord+"%')";
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
			if(conn!=null)	conn.close();
			if(stmt!=null)	stmt.close();
			if(rs!=null)	rs.close();
		}
		return totCnt;
	}
	
	public List<RecuBoard> recuList(int startRow, int endRow) throws SQLException {
		List<RecuBoard> list=new ArrayList<RecuBoard>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="Select * from (select rownum rn ,a.* from"
					+"(select * from board b join stdchk s on b.stdnum=s.stdnum where b_type='2' order by b.stdnum desc) a)"
					+"where rn between ? and ?";

		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecuBoard board=new RecuBoard();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setViews(rs.getInt("views"));
				board.setImagepath(rs.getString("imagepath"));
				board.setStddiv(rs.getString("stddiv"));
				board.setStdstart_date(rs.getString("stdstart_date"));
				board.setStdend_date(rs.getString("stdend_date"));
				board.setStdstatus(rs.getString("stdstatus"));
				board.setStdpn(rs.getString("stdpn"));
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

	public List<RecuBoard> searchList(String keyWord, int startRow, int endRow) throws SQLException {
		List<RecuBoard> list=new ArrayList<RecuBoard>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="Select * from (select rownum rn ,a.* from (select * from board b join stdchk s on b.stdnum=s.stdnum where b.b_type='2' "
				+ "and (title like '%"+keyWord+"%' or content like '%"+keyWord+"%' or id like '%"+keyWord+"%') order by b.stdnum desc) a)"
				+ "where rn between ? and ?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecuBoard board=new RecuBoard();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setViews(rs.getInt("views"));
				board.setImagepath(rs.getString("imagepath"));
				board.setStddiv(rs.getString("stddiv"));
				board.setStdstart_date(rs.getString("stdstart_date"));
				board.setStdend_date(rs.getString("stdend_date"));
				board.setStdstatus(rs.getString("stdstatus"));
				board.setStdpn(rs.getString("stdpn"));
				list.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return list;
	}
	
	public int recuInsert(RecuBoard board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql1="select nvl(max(b_num), 0) from board where b_type='2'";
		String sql2="insert into stdchk values(SEQ_STDNUM.NEXTVAL,?,?,to_date(?,'yyyy-MM-dd'),to_date(?,'yyyy-MM-dd'),"
					+ "?,0,?,to_date(?,'yyyy-MM-dd'),to_date(?,'yyyy-MM-dd'))";
		String sql3="insert into board values(2,?,?,sysdate,?,?,?,0,null,null,0,0,?,SEQ_STDNUM.CURRVAL)";
		int result=0;

		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();
			rs.next();
			int num=rs.getInt(1)+1;				
			pstmt.close();
			rs.close();
		
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, board.getStdreg_id());
			pstmt.setString(2, board.getStdtitle());
			pstmt.setString(3, board.getStdstart_date());
			pstmt.setString(4, board.getStdend_date());
			pstmt.setString(5, board.getStdpn());
			pstmt.setString(6, board.getStddiv());
			pstmt.setString(7, board.getRecru_date());
			pstmt.setString(8, board.getRecu_date());
			int result1=pstmt.executeUpdate();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql3);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getTitle());
			pstmt.setString(5, board.getImagepath());
			pstmt.setInt(6, num);		
			int	result2=pstmt.executeUpdate();
			System.out.println(result1);
			
			
			System.out.println(result2);
			if(result1==1&&result2==1) {
				result=1;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return result;
	}
	
	public void updateViews(int b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update board set views=views+1 where b_type='2' and b_num=?";
	
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
	
	public RecuBoard recuContent(int b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from board b join stdchk s on b.stdnum=s.stdnum where b_type='2' and b_num=?";		
		RecuBoard board=new RecuBoard();
		SimpleDateFormat fomat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setUploaddate(rs.getDate("uploaddate"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setImagepath(rs.getString("imagepath"));
				board.setViews(rs.getInt("views"));
				board.setStdnum(rs.getInt("stdnum"));
				board.setStdtitle(rs.getString("stdtitle"));
				board.setStdstart_date(fomat.format(rs.getDate("stdstart_date")));
				board.setStdend_date(fomat.format(rs.getDate("stdend_date")));
				board.setStdpn(rs.getString("stdpn"));
				board.setStdstatus(rs.getString("stdstatus"));
				board.setStddiv(rs.getString("stddiv"));
				board.setRecru_date(fomat.format(rs.getDate("recru_date")));
				board.setRecu_date(fomat.format(rs.getDate("recu_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return board;
	}
	
	public int applyCount(int stdnum) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from stdapply where stdnum=? and apply_chk='1'";
		int applyCount=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stdnum);
			rs=pstmt.executeQuery();
			if(rs.next())
				applyCount=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return applyCount;
	}
	
	public List<Board> readComment(int b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from board where b_type='2' and ref=? and re_level in('1', '2') order by re_step, re_level, b_num";
		List<Board> list=new ArrayList<Board>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board=new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setId(rs.getString("id"));
				board.setCommcontent(rs.getString("commcontent"));
				board.setCommdate(rs.getDate("commdate"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));

				list.add(board);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return list;
	}
	
	public int insertComment(Board board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql1="select nvl(max(b_num), 0) from board where b_type='2'";
		String sql2="select nvl(max(re_step), 0) from board where b_type='2' and ref=?";
		String sql3="insert into board values(2,?,?,null,null,null,null,null,?,sysdate,1,?,?,null )";
					//타입,넘버,id,date,내용,제목,이미지,뷰,댓글,댓글시간,re_level,re_step,ref,스터디넘버
		int result=0;
	
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();
			rs.next();
			int num=rs.getInt(1)+1;	
			pstmt.close();
			rs.close();
			
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, board.getRef());
			rs=pstmt.executeQuery();
			rs.next();
			int re_step=rs.getInt(1)+1;
			pstmt.close();
			rs.close();
			
			pstmt=conn.prepareStatement(sql3);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getCommcontent());
			pstmt.setInt(4, re_step);
			pstmt.setInt(5, board.getRef());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return result;
	}
	
	public int insertreComment(Board board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql1="select nvl(max(b_num), 0) from board where b_type='2'";
		String sql2="insert into board values(2,?,?,null,null,null,null,null,?,sysdate,2,?,?,null )";
										//타입,넘버,id,date,내용,제목,이미지,뷰,댓글,댓글시간,re_level,re_step,ref,스터디넘버
		int result=0;
	
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();
			rs.next();
			int num=rs.getInt(1)+1;	
			pstmt.close();
			rs.close();

			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getCommcontent());
			pstmt.setInt(4, board.getRe_step());
			pstmt.setInt(5, board.getRef());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return result;
	}

	public int insertApplyStd(StdAppply stdApply) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into STDAPPLY values(?,?,sysdate,null,0,? )";
		int result=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stdApply.getStdnum());
			pstmt.setString(2, stdApply.getParticipant_id());
			pstmt.setString(3, stdApply.getApply_content());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}

	public int updateApplyStd(StdAppply stdApply) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update STDAPPLY set stdapply_date=sysdate, apply_chk='0', apply_content=? where stdnum=? and participant_id=?";
		int result=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stdApply.getApply_content());
			pstmt.setInt(2, stdApply.getStdnum());
			pstmt.setString(3, stdApply.getParticipant_id());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	
	public int checkStdApply(String id, int stdnum) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from STDAPPLY where stdnum=? and participant_id=?"; 
		int result=-1;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stdnum);
			pstmt.setString(2, id);;
			rs=pstmt.executeQuery();
			if(rs.next())
				result=rs.getInt("apply_chk");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return result;
	}

	public int cancleApplyStd(int stdnum, String id) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update STDAPPLY set apply_chk='2' where stdnum=? and participant_id=?";
		int result=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stdnum);
			pstmt.setString(2, id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}	
		return result;
	}
	
	public int recuDelete(int rdf_b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from board where ref=? and b_type='2'";
		int result=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rdf_b_num);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	
	public int commDelete(int ref, int re_step) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from board where ref=? and re_step=? and b_type='2'";
		int result=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_step);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	
	
	public int recommDelete(int rdf_b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from board where b_num=? and b_type='2'";
		int result=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rdf_b_num);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	
	public int recuUpdate(RecuBoard board) throws SQLException {
			Connection conn=null;
			PreparedStatement pstmt=null;
			String sql1="update stdchk set stdtitle=?, stdstart_date=to_date(?,'yyyy-MM-dd'), "
						+ "stdend_date=to_date(?,'yyyy-MM-dd'), stdpn=?, stddiv=?, recru_date=?, recu_date=? where stdnum=?";
			String sql2="update board set content=?, title=?, imagepath=? where b_num=? and b_type='2'";
			int result=0;

		try {
			conn=getConnection();
	
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, board.getStdtitle());
			pstmt.setString(2, board.getStdstart_date());
			pstmt.setString(3, board.getStdend_date());
			pstmt.setString(4, board.getStdpn());
			pstmt.setString(5, board.getStddiv());
			pstmt.setString(6, board.getRecru_date());
			pstmt.setString(7, board.getRecu_date());
			pstmt.setInt(8, board.getStdnum());
			int result1=pstmt.executeUpdate();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, board.getContent());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getImagepath());
			pstmt.setInt(4, board.getB_num());
			int	result2=pstmt.executeUpdate();

			if(result1==1&&result2==1) {
				result=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}

	public String commUpdateForm(int b_num) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select commcontent from board where b_num=? and b_type='2'";
		String commcontent=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs=pstmt.executeQuery();
			if(rs.next())
				commcontent=rs.getString("commcontent");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		return commcontent;
	}
	
	public int commUpdatePro(Board board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update board set commcontent=? where b_num=? and b_type='2'";
		int result=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getCommcontent());
			pstmt.setInt(2, board.getB_num());
			result=pstmt.executeUpdate();
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
		}
		return result;
	}
	
	
	
	
}
