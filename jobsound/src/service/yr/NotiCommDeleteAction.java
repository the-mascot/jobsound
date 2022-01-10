package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiCommDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String b_type = request.getParameter("b_type");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		int result = mid.notiCommDelete(b_num, b_type);
		
		System.out.println("NotiCommDeleteAction result ::::"+result);
		
		return null;
	}

}
