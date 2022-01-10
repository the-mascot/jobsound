package service.sw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sw.BoardDao;
import service.CommandProcess;

public class MngBoardDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int b_type = Integer.parseInt(request.getParameter("b_type"));	
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			
			BoardDao bd = BoardDao.getInstance();
			int result = bd.boardDelete(b_type, b_num);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "mngBoardDeleteChk.jsp";
	}

}
