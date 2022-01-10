package service.gm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsUpProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			try {
				request.setCharacterEncoding("utf-8");
				HttpSession session = request.getSession();			// 세션 저장
				String id = (String)session.getAttribute("sessionID");
				int b_num = Integer.parseInt(request.getParameter("b_num"));
				String pageNum = request.getParameter("pageNum");
				String b_type = request.getParameter("b_type");
				System.out.println("NewsUpProAction b_num->"+b_num);
				System.out.println("NewsUpProAction b_type->"+b_type); // 글쓰기 hidden에서 값 가져오기
				System.out.println("NewsUpProAction id->"+id);
				
				Board board = new Board();
				board.setB_type(request.getParameter("b_type"));		// 받아오는 데이터 board에 저장
				board.setB_num(Integer.parseInt(request.getParameter("b_num")));
				board.setId(request.getParameter("id"));
				board.setTitle(request.getParameter("title"));
				board.setContent(request.getParameter("content"));
				board.setImagepath(request.getParameter("imagepath"));
				
				BoardDao bd = BoardDao.getInstance();
				int result = bd.update(board);
				System.out.println("NewsUpProAction setAttribute result->"+result);
				System.out.println("NewsUpProAction setAttribute b_num->"+b_num);
				System.out.println("NewsUpProAction setAttribute pageNum->"+pageNum);
				System.out.println("NewsUpProAction setAttribute b_type->"+b_type);
				System.out.println("NewsUpProAction setAttribute imagepath->"+board.getImagepath());
				
				request.setAttribute("b_num", b_num);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("b_type", b_type);
				request.setAttribute("result", result);
				request.setAttribute("title", board.getTitle());
				request.setAttribute("id", board.getId());
				request.setAttribute("content", board.getContent());	
				
				System.out.println("NewsUpProAction callback->"+result);
				
			} catch (Exception e) {
				System.out.println("NewsUpProAction ->"+e.getMessage());
			}
		
		return "newsUpPro.jsp";
	}

}
