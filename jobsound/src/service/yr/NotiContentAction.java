package service.yr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.Board;
import dao.yr.MemberInfoDao;
import service.CommandProcess;


public class NotiContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			String b_type = "1";
//			String pageNum = request.getParameter("pageNum");
			System.out.println("b_num==============" + b_num);
//			System.out.println("pageNum==================" + pageNum);
			MemberInfoDao mid = MemberInfoDao.getInstance();
			mid.views(b_num);
			Board board = mid.select(b_num, b_type); // 게시물 조회용
			List<Board> list = mid.commList(b_num, b_type); // 댓글 조회용
			int listSize = list.size();
			
			request.setAttribute("b_num", b_num);
			request.setAttribute("b_type", b_type);
//			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
			request.setAttribute("list", list);
			request.setAttribute("b_type", b_type);
			request.setAttribute("listSize", listSize);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return "notiContent.jsp";
	}

}
