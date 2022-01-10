package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sh.BoardDao;
import service.CommandProcess;

public class ComuCommAddAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession();
		BoardDao bd = BoardDao.getInstance();
		request.setCharacterEncoding("utf-8");
		
		
		String b_num = request.getParameter("b_num"); // 새로 작성될 댓글 번호X, 댓글 작성 후에 돌아갈 게시글 번호, 댓글이 참조할 부모 게시글 번호(ref)
		String id = (String)session.getAttribute("sessionID"); // 작성자 아이디
		String commcontent = request.getParameter("commcontent"); // 댓글 내용
		
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
		} else  if (commcontent==null || commcontent=="") {
			int result = -1;
			request.setAttribute("b_num", b_num);
			request.setAttribute("result", result);
			return "comuAddCommPro.jsp";
		} else {
			int result = bd.addComm(b_num, commcontent, id);
			System.out.println("기냥 댓글 진입");
			request.setAttribute("b_num", b_num);
			request.setAttribute("result", result);
			return "comuAddCommPro.jsp";
		}
	}

}
