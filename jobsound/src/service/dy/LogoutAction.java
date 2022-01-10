package service.dy;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

public class LogoutAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		session.removeAttribute("sessionID");
		session.invalidate();
		
		String referer = request.getHeader("Referer"); // 이전 페이지 정보
		String direct = referer.substring(referer.indexOf("/dy/")); // 이전 페이지 정보에서 /sh/부터 자름 ex)/sh/comuContent.do?b_num=24&pageNum=1&b_type=3
		
		
		return "main.jsp";
	}

}
