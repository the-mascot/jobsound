package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sh.BoardDao;
import service.CommandProcess;

public class ComuContentDelAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDao bd = BoardDao.getInstance();
		String b_num = request.getParameter("b_num");
		String id = (String)request.getSession().getAttribute("sessionID");
		
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
		} else {
			int result = bd.comuBoardDel(b_num);
			
			request.setAttribute("result", result);
			
			return "comuContentDelPro.jsp";
		}
	}

}
