package service.es;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.RecuBoard;
import dao.es.RecuBoardDao;
import service.CommandProcess;

public class RecuUpProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session=request.getSession();
			request.setCharacterEncoding("utf-8");
			RecuBoard board=new RecuBoard();
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			String id=(String)session.getAttribute("sessionID");
			String pageNum=request.getParameter("pageNum");
			String pageSize=request.getParameter("pageSize");
			int b_num=Integer.parseInt(request.getParameter("b_num"));
			
			board.setB_num(b_num);
			board.setContent(request.getParameter("content"));
			board.setTitle(request.getParameter("title"));
			board.setImagepath(request.getParameter("imagepath"));
			board.setStdnum(Integer.parseInt(request.getParameter("stdnum")));
			board.setStdtitle(request.getParameter("stdtitle"));
			board.setStdstart_date(request.getParameter("stdstart_date"));
			board.setStdend_date(request.getParameter("stdend_date"));
			board.setRecru_date(request.getParameter("recru_date"));
			board.setRecu_date(request.getParameter("recu_date"));
			board.setStddiv(request.getParameter("stddiv"));
			board.setStdpn(request.getParameter("stdpn"));
			
			int result=rbd.recuUpdate(board);
			
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("b_num", b_num);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
		return "recuUpdatePro.jsp";
	}

}
