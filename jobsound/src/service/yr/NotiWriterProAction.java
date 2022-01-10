package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiWriterProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		System.out.println("==========notiWriteProAction===========");
		String id = request.getParameter("id");
		int b_num = Integer.parseInt(request.getParameter("b_num")); 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("notiWritePro id::::"+id);
		System.out.println("notiWritePro b_num::::"+b_num);
		System.out.println("notiWritePro title::::"+title);
		System.out.println("notiWritePro content::::"+content);
		
		MemberInfoDao mid = MemberInfoDao.getInstance();
		int result = mid.notiWrite(b_num, id, title, content);
		
		request.setAttribute("result", result);
		
		return "notiWriterPro.jsp";
	}

}
