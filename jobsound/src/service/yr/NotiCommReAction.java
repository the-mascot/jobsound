package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiCommReAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("=======NotiCommReAction ===== 시작");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		String commContent = request.getParameter("commContent");
		String b_type = request.getParameter("b_type");
		int b_num = Integer.parseInt(request.getParameter("b_num"));

		int maxB_num = mid.getBnumMaxCnt(b_type); //board number 의 최댓값(max값)을 가져와서 담는다
		int maxNum = maxB_num + 1;
		
		System.out.println("NotiCommReAction id ::::::::" + id);
		System.out.println("NotiCommReAction b_num ::::::::" + b_num);
		System.out.println("NotiCommReAction commContent ::::::::" + commContent);
		
		int result = mid.notiReWriteComm(maxNum, id, b_num, commContent);
		
		request.setAttribute("result", result);
		request.setAttribute("b_num", b_num);
		request.setAttribute("b_type", b_type);
		
		return "notiCommWritePro.jsp";
	}

}
