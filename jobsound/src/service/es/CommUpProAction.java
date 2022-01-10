package service.es;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.Board;
import dao.es.RecuBoardDao;
import service.CommandProcess;

public class CommUpProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
			
		request.setCharacterEncoding("utf-8");
		RecuBoardDao rbd=RecuBoardDao.getInstance();
		Board board=new Board();
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		String b_num=request.getParameter("b_num");

		board.setB_num(Integer.parseInt(request.getParameter("commb_num")));
		board.setCommcontent(request.getParameter("commcontent"));
		int result=rbd.commUpdatePro(board);			

		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("b_num", b_num);
	
		return "recuCommUpPro.jsp";
	}
}
