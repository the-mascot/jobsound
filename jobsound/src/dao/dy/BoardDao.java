package dao.dy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


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
	


	


//======================================================================================류동엽 추가
//================================================================================================
	
	
	
	
	
	
	// 10.15 메인게시판에 뜰 제목들 보내주는거
//============뉴스게시판 6================	
	
	public ArrayList<Board> getNewsList() throws SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TITLE,IMAGEPATH,B_NUM FROM (SELECT ROW_NUMBER() OVER(ORDER BY A.B_NUM ) AS ROW_NUM,A.* "
					+ "FROM BOARD A "
					+ "ORDER BY A.B_NUM DESC) "
					+ "WHERE TITLE IS NOT NULL AND ROWNUM<=4 AND B_TYPE = 6 AND RE_LEVEL='0'";
		
		ArrayList<Board> newslist = new ArrayList<Board>();
		
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt=0;//검사용
			
			System.out.println("boarddao 리스트값 검사중...");
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				board.setImagepath(rs.getString("imagepath"));
				newslist.add(board);
				
				//				검사
				System.out.println("뉴스 검사 시작...");
				System.out.println("newslist값 검사: ->"+newslist.get(cnt).getTitle());
				System.out.println("newslist값 검사: ->"+newslist.get(cnt).getImagepath());
				cnt++;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}
		
		return newslist;
		
	}

//===========공지사항 1=======================
	public ArrayList<Board> getNotiList() throws SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TITLE,IMAGEPATH,B_NUM FROM (SELECT ROW_NUMBER() OVER(ORDER BY A.B_NUM ) AS ROW_NUM,A.* "
				+ "FROM BOARD A "
				+ "ORDER BY A.B_NUM DESC) "
				+ "WHERE TITLE IS NOT NULL AND ROWNUM<=7 AND B_TYPE = 1 AND RE_LEVEL='0'";
		
		ArrayList<Board> notilist = new ArrayList<Board>();
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt=0;
			
			System.out.println("boarddao 리스트값 검사중...");
						
			while(rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				notilist.add(board);
				
				
				System.out.println("notilist값 검사: ->"+notilist.get(cnt).getTitle());   //get(1) 검사 , 다음값들어가고 get(2)검사
				cnt++;	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}	
		
		return notilist;
		
	}
	
	
//==============커뮤니티 3===================== 	
	public ArrayList<Board> getComuList() throws SQLException{
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TITLE,IMAGEPATH,B_NUM FROM (SELECT ROW_NUMBER() OVER(ORDER BY A.B_NUM ) AS ROW_NUM,A.* "
				+ "FROM BOARD A "
				+ "ORDER BY A.B_NUM DESC) "
				+ "WHERE TITLE IS NOT NULL AND ROWNUM<=6 AND B_TYPE = 3 AND RE_LEVEL='0'";
		
		ArrayList<Board> comulist = new ArrayList<Board>();
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt=0;
			
			System.out.println("boarddao 리스트값 검사중...");
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				comulist.add(board);
				
				
				System.out.println("comulist값 검사: ->"+comulist.get(cnt).getTitle());   //get(1) 검사 , 다음값들어가고 get(2)검사
				cnt++;	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}	
		
		return comulist;
		
	}
	
	
	
//===============스터디모집 2====================
	public ArrayList<Board> getStudyList() throws SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TITLE,IMAGEPATH,B_NUM FROM (SELECT ROW_NUMBER() OVER(ORDER BY A.B_NUM ) AS ROW_NUM,A.* "
					+ "FROM BOARD A "
					+ "ORDER BY A.B_NUM DESC) "
					+ "WHERE TITLE IS NOT NULL AND ROWNUM<=6 AND B_TYPE = 2 AND RE_LEVEL='0'";
		
		ArrayList<Board> studylist = new ArrayList<Board>();
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt=0;
			
			System.out.println("boarddao 리스트값 검사중...");
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setTitle(rs.getString("title"));
				studylist.add(board);
				
				
				System.out.println("studylist값 검사: ->"+studylist.get(cnt).getTitle());   //get(1) 검사 , 다음값들어가고 get(2)검사
				cnt++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)	conn.close();
			if(pstmt!=null)	pstmt.close();
			if(rs!=null)	rs.close();
		}	
		
		return studylist;
		
	}
	
	
	
}
