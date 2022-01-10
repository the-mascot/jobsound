package service.gm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			BoardDao bd = BoardDao.getInstance();
			
		try {
			request.setCharacterEncoding("utf-8");
			String search = request.getParameter("search");
			int totCnt = bd.getSearchCnt(search);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			String pageSize = request.getParameter("pageSize");
			if(pageSize == null || pageSize.equals(""))
				pageSize = "10";
			int currentPage=Integer.parseInt(pageNum);
			int blockSize=10;
			int startRow=(currentPage-1)*Integer.parseInt(pageSize)+1;
			int endRow=startRow+Integer.parseInt(pageSize)-1;
			int startNum=totCnt-startRow+1;
			List<Board> list=bd.searchList(search, startRow, endRow);
			int pageCnt=(int) Math.ceil((double)totCnt/Integer.parseInt(pageSize));
			int StartPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=StartPage+blockSize-1;
			
			if(endPage>pageCnt)
				endPage=pageCnt;
			request.setAttribute("keyWord", search);
			request.setAttribute("b_type", "6");
			request.setAttribute("today", format.format(new Date())); 
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", StartPage);
			request.setAttribute("endPage", endPage);
		} catch (Exception e) {
			System.out.println("NewsUpProAction ->"+e.getMessage());
		}
	return "newsBoardList.jsp";
	}
}
