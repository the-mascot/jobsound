package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sh.BoardDao;
import service.CommandProcess;

public class ComuCommAddReAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		BoardDao bd = BoardDao.getInstance();
		request.setCharacterEncoding("utf-8");
		
		String bnum = request.getParameter("bnum"); // 부모 게시글번호 
		String b_num = request.getParameter("b_num"); // 답글 달 댓글의 글번호
		System.out.println("b_num 넘어왔니?"+b_num);
		String id = (String)session.getAttribute("sessionID");
		String commcontent = request.getParameter("commcontent");
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
		} else  {
			int result = bd.addCommRe(b_num, commcontent, id);
			System.out.println("대댓글 성공");
			System.out.println("result는?"+result);
			System.out.println("bnum는?(이전 글번호)"+bnum);
			request.setAttribute("b_num", bnum);
			request.setAttribute("result", result);
			return "comuAddCommPro.jsp";
		} 
	}

}
