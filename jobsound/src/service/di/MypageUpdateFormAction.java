package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.MemberInfo;
import dao.di.MemberInfoDao;
import service.CommandProcess;

public class MypageUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=====MypageUpdateFormAction=====");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		MemberInfoDao mid = MemberInfoDao.getInstance();
		MemberInfo mi = mid.memberInfo(id);
//		request.setAttribute("name", mi.getName());
//		request.setAttribute("id", id);
//		request.setAttribute("nickname", mi.getNickname());
//		request.setAttribute("tel", mi.getTel());
//		request.setAttribute("email", mi.getEmail());
//		request.setAttribute("addr", mi.getAddr());
		request.setAttribute("mi", mi);
		return "mypageUpdateForm.jsp";
	}

}
