package service.dy;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dy.MemberDao;
import service.CommandProcess;

public class acntPwAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
//		HttpSession session=request.getSession();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		//검사해주는 부분
		System.out.println("acntidAction start...");
		System.out.println("acntidAction name->"+id);
		System.out.println("acntidAction name->"+name);
		System.out.println("acntidAction email->"+email);
		
		MemberDao md = MemberDao.getInstance();
		
		
		String passwd = md.acntPwCheck(id, name, email);
		System.out.println("acntPwAction passwd->"+passwd);
		
		request.setAttribute("passwd", passwd);
		 		
		if(passwd!=null) {
			
			return "pwSch.jsp";
			
		}else {
			return "acntpwConfirm.jsp";
		}
			
	}
	
}
