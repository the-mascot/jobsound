package service.yr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.yr.Board;
import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiSearchListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("==========notiSearchAction============");
			String search = request.getParameter("search");
			String b_type = request.getParameter("b_type");
			MemberInfoDao mid = MemberInfoDao.getInstance();
			int totCnt = mid.getTotalCnt(b_type);
			
			String pageNum = request.getParameter("pageNum");
			String pageSize=request.getParameter("pageSize");	// 10개씩 보기 받아오기
			if(pageSize==null||pageSize.equals(""))
				pageSize="10";
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";   }
			int currentPage = Integer.parseInt(pageNum); //pageNum이 int형으로 파싱 한 값임
			int blockSize = 10;
			int startRow = (currentPage - 1) *	Integer.parseInt(pageSize) + 1;
			int endRow = startRow + Integer.parseInt(pageSize) - 1;
			List<Board> list = mid.notiSearch(search, b_type, startRow, endRow);
			int startNum = totCnt - startRow + 1;
			System.out.println("notiSearch search::::"+search);
			System.out.println("notiSearchList::::::"+list);
			int pageCnt = (int) Math.ceil((double) totCnt / Integer.parseInt(pageSize));
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt)
				endPage = pageCnt;
			int listSize = list.size();
		

	        request.setAttribute("b_type", b_type);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("cnt", (currentPage -1)*Integer.parseInt(pageSize));
			request.setAttribute("listSize", listSize);
			request.setAttribute("search", search);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "notiSearchList.jsp";
	}

}
