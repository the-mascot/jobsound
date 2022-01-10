package service.dy;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dy.MemberDao;
import service.CommandProcess;

public class acntIdAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		//검사해주는 부분
		System.out.println("acntidAction start...");
		System.out.println("acntidAction name->"+name);
		System.out.println("acntidAction email->"+email);
		
		MemberDao md = MemberDao.getInstance();
		String id = md.acntidCheck(name, email);
		System.out.println("acntidAction id->"+id);
		
		request.setAttribute("id", id);
		 		
		if(id!=null) {
			
			return "idSch.jsp";
			
		}else {
			return "acntidConfirm.jsp";
		}
			
	}
	
}
