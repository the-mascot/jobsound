package service.gm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsDelProAction implements CommandProcess {

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
			
			int result = bd.delete(b_num, b_type);	// id 추가
			
			request.setAttribute("b_num", b_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("b_type", b_type);
			request.setAttribute("result", result);
			
			System.out.println("result callback : "+request.getAttribute("result"));
			System.out.println("NewsDelProAction session->"+id);
			
		} catch (Exception e) {
			System.out.println("NewsDelProAction->"+e.getMessage());
		}
		
		return "newsDelPro.jsp";
	}

}
