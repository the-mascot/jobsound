package service.gm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

public class NewsWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionID");
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) pageNum = "1";
															// 댓글 처리
			request.setAttribute("pageNum", pageNum);
			
			
			System.out.println("NewsWriteFormAction->"+id);
		} catch (Exception e) {
			System.out.println("WriteFormAction->"+e.getMessage());
		}		
		return "newsWriteFormBoard.jsp";
	}

}
