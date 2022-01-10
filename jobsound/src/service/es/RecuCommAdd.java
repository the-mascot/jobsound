package service.es;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.es.Board;
import dao.es.RecuBoardDao;
import service.CommandProcess;

public class RecuCommAdd implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("utf-8");
		int b_num=Integer.parseInt(request.getParameter("b_num")); 
		RecuBoardDao rbd=RecuBoardDao.getInstance();
		Board board=new Board();
		int result=0;
		
		board.setId((String)session.getAttribute("sessionID"));
		board.setCommcontent(request.getParameter("commcontent"));
		board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		board.setRef(b_num);
		
		if(board.getRe_level()==1) {
			result=rbd.insertComment(board);	
		}
		else {
			board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
			result=rbd.insertreComment(board);			
		}
		

		request.setAttribute("result", result);
		request.setAttribute("b_num", b_num);
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return "recuCommAddPro.jsp";
	}

}
