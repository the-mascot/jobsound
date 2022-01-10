package dao.sh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDao {

	
	private static MemberDao instance;
	

	private MemberDao() {}
	
	public static MemberDao getInstance() { // 싱글턴
		if (instance == null) {	
			instance = new MemberDao();		}
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
	
	public int loginCheck(String id, String passwd) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbPW = ""; 
		int result = -1;

		try {
			String sql = "SELECT passwd FROM memberinfo WHERE id=?";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				dbPW = rs.getString("passwd"); 

				if (dbPW.equals(passwd)) 
					result = 1; 
				else 				 
					result = 0; 
				
			} else {
				result = -1;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				System.out.println(e.getMessage());;
			}
		}
		return result;
	}
}
