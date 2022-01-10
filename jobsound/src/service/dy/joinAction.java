package service.dy;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dy.MemberDao;
import dao.dy.MemberInfo;
import service.CommandProcess;

public class joinAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		// MemberInfo DTO 선언 
		request.setCharacterEncoding("UTF-8");
		MemberInfo mi = new MemberInfo();
		mi.setId(request.getParameter("id"));
		mi.setPasswd(request.getParameter("passwd"));
		mi.setName(request.getParameter("name"));
		mi.setGender(request.getParameter("gender"));
		String birth = request.getParameter("birth");
		birth = birth.replace("-", "");
		mi.setBirth(birth);
		mi.setEmail(request.getParameter("email"));
		String tel = request.getParameter("tel");
		tel = tel.replace("-", "");
		mi.setTel(request.getParameter("tel"));
		mi.setNickname(request.getParameter("nickname"));

		System.out.println("joinAction start...");
		System.out.println("joinAction id->"+request.getParameter("id"));
		System.out.println("joinAction passwd->"+request.getParameter("passwd"));
		System.out.println("joinAction name->"+request.getParameter("name"));
		System.out.println("joinAction gender->"+request.getParameter("gender"));
		System.out.println("joinAction birth->"+request.getParameter("birth"));
		System.out.println("joinAction email->"+request.getParameter("email"));
		System.out.println("joinAction tel->"+request.getParameter("tel"));
		System.out.println("joinAction nickname->"+request.getParameter("nickname"));

		MemberDao md = MemberDao.getInstance();
		int result = md.joinUpdate(mi);
		System.out.println("joinAction info->"+result);  
		
		request.setAttribute("result", result);
		return "joinConfirm.jsp";
	}

}
