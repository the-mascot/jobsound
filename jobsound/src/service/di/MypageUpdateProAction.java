package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.di.MemberInfo;
import dao.di.MemberInfoDao;
import service.CommandProcess;

public class MypageUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberInfo mi = new MemberInfo();
		int passwdchk = 0;
		mi.setId(request.getParameter("id"));
		mi.setTel(request.getParameter("tel"));
		mi.setEmail(request.getParameter("email"));
		mi.setAddr(request.getParameter("addr"));
//		System.out.println("id::::"+mi.getId());
//		System.out.println(""+mi.getPasswd());
//		System.out.println("repasswd::::"+repasswd);
//		System.out.println("id::::"+tel);
//		System.out.println("id::::"+email);
//		System.out.println("id::::"+addr);
//		
		MemberInfoDao mid = MemberInfoDao.getInstance();
		int result = mid.mypageUpdate(mi);
		request.setAttribute("mi", mi);
		request.setAttribute("result", result);
		return "mypageUpdatePro.jsp";
	}

}
