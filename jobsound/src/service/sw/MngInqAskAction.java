package service.sw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sw.Inquire;
import dao.sw.InquireDao;
import service.CommandProcess;

public class MngInqAskAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Inquire inq = new Inquire();
			request.setCharacterEncoding("utf-8");
			
			String inqnum = request.getParameter("inqnum"); 
			String content = request.getParameter("content");
			
			InquireDao inqD = InquireDao.getInstance();
			int result = inqD.Inqupdate(inqnum, content);
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "mngInqAskChk.jsp";
	}

}
