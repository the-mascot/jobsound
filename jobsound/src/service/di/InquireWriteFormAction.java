package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.InquireDao;
import service.CommandProcess;

public class InquireWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		InquireDao iqd = InquireDao.getInstance();
		int totInqNum = iqd.getInqTotalCnt();
		int inqNum = totInqNum+1;
		System.out.println("writeFormAction id:::::"+id);
		System.out.println("writeFormAction inqNum:::::"+inqNum);
		request.setAttribute("id", id);
		request.setAttribute("inqNum", inqNum);
		
		return "inquireWriteForm.jsp";
	}

}
