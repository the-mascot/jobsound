package service.gm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsCommWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionID");
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			String pageNum = request.getParameter("pageNum");
			String b_type = request.getParameter("b_type");
			
			Board board = new Board();
			board.setB_num(b_num);
			board.setCommcontent(request.getParameter("commcontent")); // 웹에서 2개만 필요
			board.setId(id);
			
			System.out.println("NewsCommWriteAction param ->"+b_num);
			System.out.println("NewsCommWriteAction param ->"+id);
			//System.out.println("NewsCommWriteAction param ->"+commcontent);
			
			BoardDao bd = BoardDao.getInstance();
			int result = bd.comminsert(board);

			request.setAttribute("result", result);
			request.setAttribute("b_num", b_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("b_type", b_type);
			System.out.println("NewsCommWriteAction result->"+result);
			
		} catch (Exception e) {
			System.out.println("NewsCommWriteAction exception->"+e.getMessage());
		}
		return "commWritePro.jsp";
	}

}
