package service.dy;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dy.Board;
import dao.dy.BoardDao;



import service.CommandProcess;


public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		
		BoardDao bd=BoardDao.getInstance();
		
		List<Board> newslist=bd.getNewsList();
		List<Board> notilist=bd.getNotiList();
		List<Board> comulist=bd.getComuList();
		List<Board> studylist=bd.getStudyList();
		
																		//입력값검사
		System.out.println("MainAction start...");
		
		
		request.setAttribute("newslist", newslist);
		request.setAttribute("notilist", notilist);
		request.setAttribute("comulist", comulist);
		request.setAttribute("studylist", studylist);
		
		return "main.jsp";
	}

}
