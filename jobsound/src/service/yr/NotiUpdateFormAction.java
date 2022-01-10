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

public class NotiUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID"); //로그인 세션용
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		
		if(id.equals("admin")) {// id 가 admin이랑 동일
			/*
			 * System.out.println("updateFormAction b_num:::::"+b_num);
			 */			
			MemberInfoDao mid = MemberInfoDao.getInstance();
			List<Board> board = mid.notiSelect(b_num);
			
			request.setAttribute("sessionID", id);
			request.setAttribute("board", board);
			
			return "notiUpdateForm.jsp";
			
		}else {
			int accountChk = 0; //관리자와 사용자의 권한 체크용. accountChk가 0이면 Update 

			request.setAttribute("b_num", b_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("accountChk", accountChk);
			return "accountChk.jsp";
		}
	}

}
