package service.yr;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.Board;
import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiListAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionID");
			MemberInfoDao mid = MemberInfoDao.getInstance();
			System.out.println("==========notiListAction============");
			String b_type="1";
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
			int startNum = totCnt - startRow + 1;
			List<Board> list = mid.notiList(startRow, endRow, b_type);
			System.out.println("notiList::::::"+list);
			int pageCnt = (int) Math.ceil((double) totCnt / Integer.parseInt(pageSize));
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt)
				endPage = pageCnt;
		

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜 2021-10-12 처럼 나오도록 설정
	        Calendar cal = Calendar.getInstance();
	        String today = sdf.format(cal.getTime());
			
	        request.setAttribute("id", id);
	        request.setAttribute("today", today);
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "notiList.jsp";
	}
}
