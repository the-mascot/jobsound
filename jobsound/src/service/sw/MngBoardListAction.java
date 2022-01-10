package service.sw;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

public class MngBoardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao.sw.BoardDao bd = dao.sw.BoardDao.getInstance();
		
		try {
			int totCnt = bd.getTotalCnt();
			String pageNum = request.getParameter("pageNum");	
			if (pageNum==null || pageNum.equals("")) {	
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);	
			
			String pageSize = request.getParameter("pageSize");
			if(pageSize == null || pageSize.equals(""))
				pageSize = "10";
					
			int blockSize = 10;
			int startRow = (currentPage - 1) * Integer.parseInt(pageSize) + 1;
			int endRow   = startRow + Integer.parseInt(pageSize) - 1;
			int startNum = totCnt - startRow + 1;
			
			List<dao.sw.Board> list = bd.list(startRow, endRow);
			
			int pageCnt = (int)Math.ceil((double)totCnt/Integer.parseInt(pageSize));
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize -1;
			if (endPage > pageCnt) endPage = pageCnt;	
			
			HttpSession session=request.getSession();
			session.setAttribute("page", 6);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			request.setAttribute("list", list);
			
			request.setAttribute("pageSize", pageSize);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "mngBoardList.jsp";
	}

}
