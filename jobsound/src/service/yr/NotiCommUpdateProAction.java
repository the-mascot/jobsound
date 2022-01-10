package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiCommUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//b_type="+b_type+"&b_num="+b_num+"&commContent="+commContent
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		String b_type = request.getParameter("b_type");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String commContent = request.getParameter("commUpdateContent");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		int result = mid.notiCommUpdate(b_type, b_num, commContent);
		int ref = mid.getRef(b_type, b_num);
		
		System.out.println("==NotiCommUpdateProAction==");
		System.out.println("b_type:::"+b_type);
		System.out.println("b_num:::"+b_num);
		System.out.println("commContent:::"+commContent);
		System.out.println("result:::"+result);
		System.out.println("ref:::"+ref);
		
		request.setAttribute("result", result);
		request.setAttribute("b_num", ref);
		return "notiCommUpdatePro.jsp";
	}

}
