package service.es;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.RecuBoard;
import dao.es.RecuBoardDao;
import service.CommandProcess;

public class RecuWriteProAction implements CommandProcess {

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
			

			board.setId(id);
			board.setContent(request.getParameter("content"));
			board.setTitle(request.getParameter("title"));
			board.setImagepath(request.getParameter("imagepath"));
			board.setStdreg_id(id);
			board.setStdtitle(request.getParameter("stdtitle"));
			board.setStdstart_date(request.getParameter("stdstart_date"));
			board.setStdend_date(request.getParameter("stdend_date"));
			board.setRecru_date(request.getParameter("recru_date"));
			board.setRecu_date(request.getParameter("recu_date"));
			board.setStddiv(request.getParameter("stddiv"));
			board.setStdpn(request.getParameter("stdpn"));
			System.out.println(board.getStdstart_date());
			System.out.println(board.getStdend_date());
			System.out.println(board.getRecru_date());
			System.out.println(board.getRecu_date());
			int result=rbd.recuInsert(board);
			
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("id", id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "recuWritePro.jsp";
	}
	
	
	
}
