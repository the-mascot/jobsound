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

public class StdApplyDao {
	private static StdApplyDao instance;
	private StdApplyDao() {};
	
	public static StdApplyDao getInstance() {
		if(instance == null) {
			instance = new StdApplyDao();
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

	public int getTotalCnt(String id) {
		int tot=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql="select count(*) from stdapply where participant_id=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

	public List<MyStudyApply> myStudyApply(int startRow, int endRow, String id) {
		List<MyStudyApply> list = new ArrayList<MyStudyApply>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from(select rownum rn, c.* from (select b.apply_chk, b.stdnum, b.participant_id, a.stdTitle, a.recru_date, a.recu_date, a.stdStatus from stdchk a join stdapply b on a.stdnum=b.stdnum where b.participant_id=? order by a.stdNum) c) where rn between ? and ?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MyStudyApply msa = new MyStudyApply();
				msa.setStdNum(rs.getInt("stdNum"));
				msa.setStdTitle(rs.getString("stdTitle"));
				msa.setRecru_date(rs.getDate("recru_date"));
				msa.setRecu_date(rs.getDate("recu_date"));
				msa.setStdStatus(rs.getString("stdStatus"));
				msa.setApply_chk(rs.getString("apply_chk"));
				list.add(msa);
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
	public int myStudyCancel(int[] stdNums, String id) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update stdapply set apply_chk=2 where participant_id=? and stdnum=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i : stdNums) {
				pstmt.setString(1, id);
				pstmt.setInt(2, i);
				result=pstmt.executeUpdate();
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
		return result;
	}

	public List<StdApply> getStudyRecruitList(String id, int stdNum) {
		List<StdApply> list = new ArrayList<StdApply>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select stdNum, participant_id, stdApply_Date, stdParti_date, apply_chk, apply_Content from stdapply where stdnum=? and participant_id!=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stdNum);
			pstmt.setString(2, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StdApply sa = new StdApply();
				sa.setStdNum(rs.getInt("stdNum"));
				sa.setParticipant_id(rs.getString("participant_id"));
				sa.setStdApply_Date(rs.getDate("stdApply_Date"));
				sa.setStdParti_date(rs.getDate("stdParti_date"));
				sa.setApply_chk(rs.getString("apply_chk"));
				sa.setApply_Content(rs.getString("apply_Content"));
				list.add(sa);
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

	public int studyApply(String participant_id, int stdNum, int apply_chk) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// if문으로 apply_chk 1=> sql에  stdParti_date를 sysdate로, apply_chk 2=> sql에  stdParti_date를 null로
		String sql = null;
		if(apply_chk == 1) {
			sql="update stdapply set apply_chk=?, stdParti_date=sysdate where PARTICIPANT_ID=? and stdNum=?";
		} else if(apply_chk == 2) {
			sql="update stdapply set apply_chk=? where PARTICIPANT_ID=? and stdNum=?";
		}
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, apply_chk);
			pstmt.setString(2, participant_id);
			pstmt.setInt(3, stdNum);
			result=pstmt.executeUpdate();
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

	public int getApplyPn(int stdNum, int applyPn) {
		int reApplyPn = applyPn;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(decode(apply_chk,'1',1)) from stdapply where stdnum=?";
		conn=getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stdNum);
			rs=pstmt.executeQuery();
			if(rs.next()) reApplyPn=rs.getInt(1);
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
		return reApplyPn;
	}
}
