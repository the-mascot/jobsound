package service.sw;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sw.Inquire;
import service.CommandProcess;

public class MngInqAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao.sw.InquireDao iqd = dao.sw.InquireDao.getInstance();
		
		try {
			int inqnum = Integer.parseInt(request.getParameter("inqnum"));
		
			Inquire inquire = iqd.inqboard(inqnum);
			
			request.setAttribute("inquire", inquire);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "mngInqAsk.jsp";
	}
}
