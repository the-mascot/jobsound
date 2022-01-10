package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sh.Board;
import dao.sh.BoardDao;
import service.CommandProcess;

public class BoardUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDao bd = BoardDao.getInstance();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		String b_num = request.getParameter("b_num");
		
		boolean chk = bd.chkWriter(b_num, id);
		
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
			
		} else if (chk==false){
			//아이디가 다름
			request.setAttribute("chk", chk);
			return "idCheckForm.jsp";
			
		} else {
			//정상 실행
			Board board = bd.boardUpdateForm(b_num);
			
			request.setAttribute("board", board);
			request.setAttribute("id", id);
			return "comuWriteForm.jsp";
		}
	}

}
