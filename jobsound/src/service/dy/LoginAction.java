package service.dy;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dy.MemberDao;
import service.CommandProcess;

public class LoginAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		HttpSession session=request.getSession();
		
		String id = request.getParameter("id");
		String password = request.getParameter("passwd");
		//검사해주는 부분
		System.out.println("LoginAction start...");
		System.out.println("LoginAction id->"+id);
		System.out.println("LoginAction password->"+password);
		
		MemberDao md = dao.dy.MemberDao.getInstance();
		int check = md.loginCheck(id, password);
		System.out.println("LoginAction check->"+check);
		
		if(check == 0)	
		{ 
	   		request.setAttribute("fail", check);
	   		return "loginConfirm.jsp";
		}
		else if(check == -1) 
		{
			request.setAttribute("fail", check);
	   		return "loginConfirm.jsp";
		}
		else
		{
			//로그인 성공 시 보낼 페이지
	   		session.setAttribute("sessionID", id);
	   		
	   	//	return "comuBoardListdo";
	   		// 향후수정  로그인 성공여부만 살핌
	   		return "loginConfirm.jsp";
		}
   		
	}
	

}
