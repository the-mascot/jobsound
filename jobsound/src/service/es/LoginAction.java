package service.es;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.MemberInfo;
import dao.es.RecuBoardDao;
import service.CommandProcess;

public class LoginAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		HttpSession session=request.getSession();
		String id=request.getParameter("id");
		String passwd = request.getParameter("passwd");
		RecuBoardDao rbd = RecuBoardDao.getInstance();
		int result=rbd.loginCheck(id, passwd);
		
		if(result==1) {
			MemberInfo mi=rbd.selectMemberInfo(id);
			session.setAttribute("gender", mi.getGender());
			session.setAttribute("nickname", mi.getNickname());
			session.setAttribute("sessionID", id);		
		}
			
		
		request.setAttribute("result", result);
		

		return "loginPro.jsp";
	}
}
