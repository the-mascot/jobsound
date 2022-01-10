package service.sh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sh.MemberDao;
import service.CommandProcess;

public class LoginAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberDao md = MemberDao.getInstance();
		int check = md.loginCheck(id, password);
		
		if(check == 0)	
		{ 
	   		request.setAttribute("fail", "0");
	   		
	   		return "LoginForm.jsp";
		}
		else if(check == -1) 
		{
			request.setAttribute("fail", "-1");

	   		return "LoginForm.jsp";
		}
		else
		{
			//로그인 성공 시 보낼 페이지
	   		session.setAttribute("sessionID", id);
	   		
	   		return "comuBoardList.do";
		}
   		
	}
	

}
