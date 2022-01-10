package service.sh;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sh.Board;
import dao.sh.BoardDao;
import dao.sh.Comment;
import service.CommandProcess;

public class ComuContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDao bd = BoardDao.getInstance();
		Board board = new Board();
		String b_type = request.getParameter("b_type");
		String b_num = request.getParameter("b_num");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		
		System.out.println("읽을 게시글의 b_num값"+b_num);
		
		if (b_num=="" || b_num==null || b_num=="0") {
			return "null.jsp";
		} else {
			bd.readCount(b_num);
			board = bd.comuContentRead(b_num);
			List<Comment> list = bd.comuCommRead(b_num);
			if (board.getContent()==null || board.getContent()=="") {
				return "null.jsp";
			}
			
			request.setAttribute("b_type", b_type);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("board", board);
			request.setAttribute("list", list);
			request.setAttribute("b_num", b_num);
			
			return "comuContent.jsp";
		}
	}
}
