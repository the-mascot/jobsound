package service.sw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sw.MemberInfo;
import service.CommandProcess;

public class MngMemListChkAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String id = request.getParameter("id");
			dao.sw.MemberInfoDao md = dao.sw.MemberInfoDao.getInstance();
			MemberInfo mi = md.memberchk(id);
			
			request.setAttribute("mi", mi);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return "mngMemListChk.jsp";
	}
}
