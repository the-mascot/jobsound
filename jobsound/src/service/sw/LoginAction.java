package service.sw;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sw.BoardDao;
import service.CommandProcess;

public class LoginAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String password = request.getParameter("passwd");
		BoardDao bd = BoardDao.getInstance();

		try {
			int result = bd.loginCheck(id, password);

			request.setAttribute("result", result);
			session.setAttribute("sessionID", id);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "loginPro.jsp";
	}
}
