package service.gm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsDelFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionID");
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			String pageNum = request.getParameter("pageNum");
			String b_type = request.getParameter("b_type");
			BoardDao bd = BoardDao.getInstance();
			List<Board> list = bd.select(b_num, pageNum, b_type);
			
			request.setAttribute("b_num", b_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("b_type", b_type);
			
			System.out.println("NewsDelFormAction session->"+id);
		} catch (Exception e) {
			System.out.println("NewsDelFormAction->"+e.getMessage());
		}
		return "newsDelForm.jsp";
	}

}
