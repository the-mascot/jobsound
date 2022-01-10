package service.gm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsCommUpProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
			{
			request.setCharacterEncoding("utf-8");
			System.out.println("proAcion 시작--------------------------------");
			String con_num = request.getParameter("con_num");	// 돌아갈 게시글 번호
			String b_num = request.getParameter("b_num"); // 수정할 댓글 번호
			String commcontent = request.getParameter("editContent"); // 수정한 내용
			String b_type = request.getParameter("b_type");
			
			System.out.println("proaction ->"+con_num);
			System.out.println("proaction ->"+commcontent);
			System.out.println("proaction ->"+b_num);
			
			BoardDao bd = BoardDao.getInstance();
			int result = bd.commupadate(b_num, commcontent);
			request.setAttribute("b_num", con_num);
			request.setAttribute("result", result);
			request.setAttribute("b_type", b_type);
			System.out.println("proaction->"+result);
			
		
		return "commUpPro.jsp";
	}

}
