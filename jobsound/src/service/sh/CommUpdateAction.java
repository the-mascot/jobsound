package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sh.BoardDao;
import service.CommandProcess;

public class CommUpdateAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		BoardDao bd = BoardDao.getInstance();
		request.setCharacterEncoding("utf-8");
		
		String b_num = request.getParameter("b_num");
		String id = (String)session.getAttribute("sessionID");
		String commcontent = request.getParameter("editContent");
		String con_num = request.getParameter("con_num"); // 수정 후 돌아갈 게시글 값
		
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
		} else  {
			int result = bd.updateComm(b_num, commcontent);
			
			request.setAttribute("b_num", con_num);
			request.setAttribute("result", result);
			return "comuCommUpdatePro.jsp";
		} 
	}

}
