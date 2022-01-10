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

public class StudyRecruitDao {
	private static StudyRecruitDao instance;
	private StudyRecruitDao() {};
	
	public static StudyRecruitDao getInstance() {
		if(instance == null) {
			instance = new StudyRecruitDao();
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

	public List<StudyRecruit> studyRecruitList(String id) {
		List<StudyRecruit> list = new ArrayList<StudyRecruit>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select stdNum, stdTitle, stdPn, stdStatus from stdChk where stdReg_id=?";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StudyRecruit sr = new StudyRecruit();
				sr.setStdNum(rs.getInt("stdNum"));
				sr.setStdTitle(rs.getString("stdTitle"));
				sr.setStdPn(rs.getString("stdPn"));
				sr.setStdStatus(rs.getString("stdStatus"));
				list.add(sr);
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

	public int stdRecruitCnt(String id) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="select count(*) from stdChk where stdReg_id=?";
		ResultSet rs = null;
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				totCnt=rs.getInt(1);
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
		return totCnt;
	}
//	public List<StudyRecruit> applyPnList(int startRow, int endRow, String id) {
//		List<StudyRecruit> list = new ArrayList<StudyRecruit>();
//		List<Integer> stdNumList = new ArrayList<Integer>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql1="select stdnum from(select rownum rn, a.* from (select * from stdchk where stdReg_id=? order by stdNum desc) a) where rn between ? and ?";
//		String sql2="select count(*) from stdchk a join stdapply b on a.stdnum=b.stdnum where a.stdnum=? and b.apply_chk=2";
//		conn=getConnection();
//		try {
//			pstmt=conn.prepareStatement(sql1);
//			pstmt.setString(1, id);
//			pstmt.setInt(2, startRow);
//			pstmt.setInt(3, endRow);
//			rs=pstmt.executeQuery();
//			while(rs.next()) {
//				stdNumList.add(rs.getInt("stdNum"));
//			}
//			if(pstmt!=null) pstmt.close();
//			if(rs!=null) rs.close();
//			
//			pstmt=conn.prepareStatement(sql2);
//			for(int i:stdNumList) {
//				StudyRecruit sr = new StudyRecruit();
//				pstmt.setInt(1, i);
//				rs=pstmt.executeQuery();
//				sr.setApplyPn(rs.getInt(1));
//				list.add(sr);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn!=null) conn.close();
//				if(pstmt!=null) pstmt.close();
//				if(rs!=null) rs.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//	}
//		return list;
//	}

	public List<Integer> myStdRecNumList(int startRow, int endRow, String id) {
		List<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select stdnum from(select rownum rn, a.* from (select stdnum from stdchk where stdReg_id=? order by stdNum desc) a) where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
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

	public List<StudyRecruit> studyRecruitList(int startRow, int endRow, String id, List<Integer> myStdRecNumList) {
		List<StudyRecruit> list = new ArrayList<StudyRecruit>();
//		List<Integer> copyMyStdRecNumList = new ArrayList<Integer>(myStdRecNumList);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select stdnum, stdtitle, stdpn, stdstatus, (select count(decode(apply_chk,'1',1)) from stdapply where stdnum=?) as applypn from stdchk where stdnum=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			for(int i=0; i<myStdRecNumList.size(); i++) {
				pstmt.setInt(1, myStdRecNumList.get(i));
//				System.out.println("int i번째 i1:::::"+i);
				pstmt.setInt(2, myStdRecNumList.get(i));
//				System.out.println("int i번째 i2:::::"+i);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					StudyRecruit sr = new StudyRecruit();
					sr.setStdNum(rs.getInt("stdNum"));
					sr.setStdTitle(rs.getString("stdTitle"));
					sr.setStdPn(rs.getString("stdPn"));
					sr.setStdStatus(rs.getString("stdStatus"));
					sr.setApplyPn(rs.getInt("applyPn"));
					list.add(sr);
				}
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
