package service.di;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.Inquire;
import dao.di.InquireDao;
import service.CommandProcess;

public class MyInquireUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		int inqNum = Integer.parseInt(request.getParameter("inqNum"));
		InquireDao iqd = InquireDao.getInstance();
		List<Inquire> list = iqd.myinquireView(id, inqNum);
		
		request.setAttribute("id", id);
		request.setAttribute("inqNum", inqNum);
		request.setAttribute("list", list);
		return "myInquireUpdateForm.jsp";
	}

}
