package service.gm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		BoardDao bd = BoardDao.getInstance();
		
		int b_num = Integer.parseInt(request.getParameter("b_num"));	// 불러올 내용의 게시판 내 글번호 - int -> String -> int 파싱
		String pageNum = request.getParameter("pageNum");
		String b_type = request.getParameter("b_type");	// 불러올 내용의 게시판 유형 - varchar2 -> int로 파싱
			
		bd.readCount(b_num); 
		List<Board> list = bd.select(b_num, pageNum, b_type);
		request.setAttribute("b_num", b_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("b_type", b_type);
				
		request.setAttribute("list", list);
		System.out.println("list callback : "+list);
		System.out.println("NewsContentAction session ->"+id);

		return "newsContent.jsp";
	}
}
