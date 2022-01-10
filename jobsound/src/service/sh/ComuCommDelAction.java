package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sh.BoardDao;
import service.CommandProcess;

public class ComuCommDelAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDao bd = BoardDao.getInstance();
		String con_num=request.getParameter("con_num");
		String b_num = request.getParameter("b_num");
		String id = (String)request.getSession().getAttribute("sessionID");
		
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
		} else {
			int result = bd.comuBoardDel(b_num);
			
			request.setAttribute("b_num", con_num);
			request.setAttribute("result", result);
			
			return "comuCommDelPro.jsp";
		}
	}

}
