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

public class StdChkDao {
	private static StdChkDao instance;
	private StdChkDao() {};
	
	public static StdChkDao getInstance() {
		if(instance == null) {
			instance = new StdChkDao();
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
		String sql="select count(*) from board where id=? and b_type=?";
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
}
