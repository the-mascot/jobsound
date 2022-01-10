package service.es;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.Board;
import dao.es.RecuBoard;
import dao.es.RecuBoardDao;
import service.CommandProcess;

public class RecuContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		try {
			HttpSession session=request.getSession();
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			String pageNum=request.getParameter("pageNum");
			String pageSize=request.getParameter("pageSize");	// 10개씩 보기 받아오기
			String id=(String)session.getAttribute("sessionID");
			int b_num=Integer.parseInt(request.getParameter("b_num"));
			System.out.println("RecuContentAction b_num="+b_num);
			rbd.updateViews(b_num);
			RecuBoard board=rbd.recuContent(b_num);
			int applyCount=rbd.applyCount(board.getStdnum());
			List<Board> list=rbd.readComment(b_num);
			int checkStd=rbd.checkStdApply(id, board.getStdnum());
			
			System.out.println("RecuContentAction list->"+list);
			System.out.println("RecuContentAction board->"+board);
			System.out.println(checkStd);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("board", board);
			request.setAttribute("list", list);
			request.setAttribute("applyCount", applyCount);
			request.setAttribute("b_num", b_num);
			request.setAttribute("checkStd", checkStd);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "recuContent.jsp";
	}
	
	
}
