package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.InquireDao;
import service.CommandProcess;

public class MyInquireUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("sessionID");
		int inqNum = Integer.parseInt(request.getParameter("inqNum"));
		System.out.println("myInquire updatePro inqNum param::::"+inqNum);
		String inqTitle = request.getParameter("inqTitle");
		String inqContent = request.getParameter("inqContent");
		
		InquireDao iqd = InquireDao.getInstance();
		int result = iqd.myInquireUpdate(id, inqNum, inqTitle, inqContent);
		
		request.setAttribute("result", result);
		return "myInquireUpdatePro.jsp";
	}

}
