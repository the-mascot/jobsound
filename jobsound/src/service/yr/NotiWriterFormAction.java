package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiWriterFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		String currentPage = request.getParameter("pageNum");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		String b_type="1";
		if (id.equals("admin")) { //아이디가 admin과 같다면 글쓰기 권한 주기
			int totCnt;
			totCnt = mid.getBnumMaxCnt(b_type);
			int b_num = totCnt + 1;
			
			request.setAttribute("id", id);
			request.setAttribute("b_num", b_num);
			return "notiWriterForm.jsp";
		} else {

			int accountChk = 1; //관리자와 사용자의 권한 체크용. accountChk가 1이면 Writer 
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("accountChk", accountChk);
			
			return "accountChk.jsp";

		}

	}
}
