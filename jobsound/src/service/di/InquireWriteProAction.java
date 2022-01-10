package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.di.InquireDao;
import service.CommandProcess;

public class InquireWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==========InquireWriteProAction===========");
		String id = request.getParameter("id");
		int inqNum = Integer.parseInt(request.getParameter("inqNum"));
		String inqTitle = request.getParameter("inqTitle");
		String inqContent = request.getParameter("inqContent");
		System.out.println("inqWritePro id::::"+id);
		System.out.println("inqWritePro inqNum::::"+inqNum);
		System.out.println("inqWritePro inqTitle::::"+inqTitle);
		System.out.println("inqWritePro inqContent::::"+inqContent);
		InquireDao iqd = InquireDao.getInstance();
		int result = iqd.myInquireWrite(inqNum, id, inqTitle, inqContent);
		
		request.setAttribute("result", result);
		return "inquireWritePro.jsp";
	}

}
