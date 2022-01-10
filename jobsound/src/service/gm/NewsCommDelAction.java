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

public class NewsCommDelAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
			try {
				HttpSession session = request.getSession();
				String id = (String)session.getAttribute("sessionID");
				int con_num = Integer.parseInt(request.getParameter("con_num"));		// 본문 글번호
				int b_num = Integer.parseInt(request.getParameter("b_num"));		// 댓글 번호
				String pageNum = request.getParameter("pageNum");
				String b_type = request.getParameter("b_type");
				
				System.out.println("CommDelAction id->"+id);
				System.out.println("CommDelAction b_num->"+con_num);
				System.out.println("CommDelAction pageNum->"+pageNum);
				System.out.println("CommDelAction b_type->"+b_type);
				
				BoardDao bd = BoardDao.getInstance();
				Board board = new Board();
				board.setB_num(b_num);
				int result = bd.commDelete(board);
				
				request.setAttribute("result", result);
				request.setAttribute("b_num", con_num);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("b_type", b_type);
				
			} catch (Exception e) {
				System.out.println("NewsCommDelAction exception->"+e.getMessage());
			}
		return "commDelPro.jsp";
	}

}
