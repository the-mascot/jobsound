package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(); // String id = (String)
		String id = (String) session.getAttribute("sessionID");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		if (id.equals("admin")) {
			MemberInfoDao mid = MemberInfoDao.getInstance();
			int result = mid.notiDelete(b_num);

			request.setAttribute("sessionID", id);
			request.setAttribute("result", result);
			return "notiDelete.jsp";
		} else {
			int accountChk = 2;
			request.setAttribute("b_num", b_num);
			request.setAttribute("accountChk", accountChk);
			return "accountChk.jsp";
		}

	}

}
