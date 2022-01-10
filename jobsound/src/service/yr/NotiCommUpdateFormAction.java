package service.yr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.Board;
import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiCommUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//b_num, commcontent
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String b_type = request.getParameter("b_type");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		List<Board> list = mid.getComment(b_type, b_num);
		System.out.println("==NotiCommUpdateFormAction==");
		System.out.println("b_num:::"+b_num);
		System.out.println("list:::"+list.toString());
		request.setAttribute("list", list);
		request.setAttribute("b_num", b_num);
		
		return "notiCommUpdateForm.jsp";
	}

}
