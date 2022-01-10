package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiCommWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		String b_type=request.getParameter("b_type");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String commContent = request.getParameter("commContent");
		System.out.println("==댓글작성 파라미터 확인==");
		System.out.println("b_type::::"+b_type);
		System.out.println("b_num::::"+b_num);
		System.out.println("commContent::::"+commContent);
		MemberInfoDao mid = MemberInfoDao.getInstance();
		int maxBnum = mid.getBnumMaxCnt(b_type);
		maxBnum = maxBnum+1;
		System.out.println("==댓글작성 maxBnum=="+maxBnum);
		int result = mid.notiCommWrite(id, b_type, maxBnum, b_num, commContent);
		System.out.println("==댓글작성 result=="+result);		
		
		request.setAttribute("result", result);
		request.setAttribute("b_type", b_type);
		request.setAttribute("b_num", b_num);
		
		return "notiCommWritePro.jsp";
	}

}
