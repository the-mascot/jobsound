package dao.di;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberInfoDao {
	private static MemberInfoDao instance;
	private MemberInfoDao() {};
	
	public static MemberInfoDao getInstance() {
		if(instance == null) {
			instance = new MemberInfoDao();
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

	public int testLogin(String id, String passwd) {
		int result=1;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select id, passwd from memberinfo where id=?";
		System.out.println("dao.id::::"+id);
		System.out.println("dao.passwd::::"+passwd);
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(passwd.equals(rs.getString("passwd"))) {
					result=1;
				} else if (!(passwd.equals(rs.getString("passwd")))) {
					result=0;
				} 
			} else result=-1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("dao.result:::::::"+result);
		return result;
	}

	public int passwdChk(String id, String passwd) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select passwd from memberinfo where id=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("passwd").equals(passwd)) {
					result=1;
				} else {
					result=0;
				}
			} else {
				result=0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}		
		return result;
	}

	public MemberInfo memberInfo(String id) {
		MemberInfo mi = null;
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		String sql="select * from memberinfo where id=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mi = new MemberInfo();
				mi.setId(rs.getString("id"));
				mi.setName(rs.getString("name"));
				mi.setNickname(rs.getString("nickname"));
				mi.setGender(rs.getString("gender"));
				mi.setTel(rs.getString("tel"));
				mi.setEmail(rs.getString("email"));
				mi.setAddr(rs.getString("addr"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return mi;
	}

	public int mypageUpdate(MemberInfo mi) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update memberinfo set tel=?, email=?, addr=? where id=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mi.getTel());
			pstmt.setString(2, mi.getEmail());
			pstmt.setString(3, mi.getAddr());
			pstmt.setString(4, mi.getId());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return result;
	}
	public int passwdUpdate(String id, String repasswd) {
		int result1 = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update memberinfo set passwd=? where id=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, repasswd);
			pstmt.setString(2, id);
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	return result1;
	}

	public int widraw(String id) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update memberinfo set name=null, nickname=null, passwd=null, gender=null, tel=null, "
				+ "email=null, birth=null, addr=null, reg_chk=null, widraw=null, regDate=null where id=?";
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
