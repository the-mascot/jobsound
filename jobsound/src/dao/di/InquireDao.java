package dao.di;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InquireDao {
	private static InquireDao instance;
	private InquireDao() {};
	
	public static InquireDao getInstance() {
		if(instance == null) {
			instance = new InquireDao();
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
	public int InqTotalCnt(String id) {
		int inqTotCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from inquire where id=?";
		conn=getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				inqTotCnt=rs.getInt(1);
			}
			System.out.println("inqTotCnt :::::: "+inqTotCnt);
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
		return inqTotCnt;
	}
	public int getInqTotalCnt() {
		int inqTotCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from inquire";
		conn=getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				inqTotCnt=rs.getInt(1);
			}
			System.out.println("inqTotCnt :::::: "+inqTotCnt);
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
		return inqTotCnt;
	}

	public List<Inquire> myInquireList(int startRow, int endRow, String id) {
		List<Inquire> list = new ArrayList<Inquire>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select * from(select rownum rn, a.* from (select * from inquire where id=? order by inqnum desc) a) where rn between ? and ?";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Inquire inquire = new Inquire();
				inquire.setInqNum(rs.getInt("inqNum"));
				inquire.setInqTitle(rs.getString("inqTitle"));
				inquire.setInqContent(rs.getString("inqContent"));
				inquire.setAskContent(rs.getString("askContent"));
				inquire.setWriteDate(rs.getDate("writeDate"));
				inquire.setAskDate(rs.getDate("askDate"));
				list.add(inquire);
			}
			System.out.println("Inquire list:::::"+list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int selectInquireDelete(int[] inqNums) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from inquire where inqNum=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for (int i : inqNums) {
				pstmt.setInt(1, i);
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

	public int myInquireWrite(int inqNum, String id, String inqTitle, String inqContent) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="insert into inquire(inqNum, id, inqTitle, inqContent, writeDate) values(?,?,?,?,sysdate)";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inqNum);
			pstmt.setString(2, id);
			pstmt.setString(3, inqTitle);
			pstmt.setString(4, inqContent);
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
		System.out.println("inqWrite result::::"+result);
		return result;
	}

	public List<Inquire> myinquireView(String id, int inqNum) {
		List<Inquire> list = new ArrayList<Inquire>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from inquire where id=? and inqNum=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, inqNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Inquire iq = new Inquire();
				iq.setInqTitle(rs.getString("inqTitle"));
				iq.setInqContent(rs.getString("inqContent"));
				iq.setAskContent(rs.getString("askContent"));
				iq.setWriteDate(rs.getDate("writeDate"));
				iq.setAskDate(rs.getDate("askDate"));
				list.add(iq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public int askCnt(int inqNum) {
		int askCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(askContent) from inquire where inqNum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inqNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				askCnt=rs.getInt(1);
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
		return askCnt;
	}

	public int myInquireUpdate(String id, int inqNum, String inqTitle, String inqContent) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update inquire set id=?, inqTitle=?, inqContent=?, askContent=null, askDate=null where inqNum=?";
		conn=getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, inqTitle);
			pstmt.setString(3, inqContent);
			pstmt.setInt(4, inqNum);
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
		System.out.println("java.sql.date 맞는지 확인, result :::: "+result);
		return result;
	}

	public int inquireDelete(String id, int inqNum) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from inquire where id=? and inqNum=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, inqNum);
			result=pstmt.executeUpdate();
			
			System.out.println("inquireDelete result::::"+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
