package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8"); // JSP 넘어온 파라미터를 UTF-8로 변환해서 받음
		System.out.println("==========notiUpdatePro===========");
		
		String id = request.getParameter("id");
		int b_num = Integer.parseInt(request.getParameter("b_num")); 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		
		int result = mid.notiUpdate(b_num, id, title, content);
		
		System.out.println("notiUpdateProAction ::::::::::" + id);
		System.out.println("notiUpdateProAction ::::::::::" + b_num);
		System.out.println("notiUpdateProAction ::::::::::" + title);
		System.out.println("notiUpdateProAction ::::::::::" + content);

		request.setAttribute("result", result);
		
		
		return "notiUpdatePro.jsp";
	}

}
