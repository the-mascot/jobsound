package service.gm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session=request.getSession();
			request.setCharacterEncoding("utf-8");
			// form 에서 입력된 파라미터 불러오기
			String pageNum = request.getParameter("pageNum");
			Board board = new Board();
			board.setId((String)session.getAttribute("sessionID"));	// 웹에서 3개의 값만 가져옴
			board.setContent(request.getParameter("content"));
			board.setTitle(request.getParameter("title"));
			board.setImagepath(request.getParameter("imagepath"));
			
			System.out.println("WriteProAction params->"+board.getId());
			System.out.println("WriteProAction params->"+board.getContent());
			System.out.println("WriteProAction params->"+board.getTitle());
			BoardDao bd = BoardDao.getInstance();
			int result = bd.insert(board);
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			
		} catch (Exception e) {
			System.out.println("ProAction Exception->"+e.getCause());
		}
		return "newsWriteProBoard.jsp";
	}

}
