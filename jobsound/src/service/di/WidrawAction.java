package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.MemberInfoDao;
import service.CommandProcess;

public class WidrawAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		System.out.println("===WidrawAction chk===");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		int result = mid.widraw(id);
		if(result>0) {
			session.invalidate();
		}
		request.setAttribute("result", result);
		
		return "widraw.jsp";
	}

}
