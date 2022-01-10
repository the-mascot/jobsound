package service.sw;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sw.MemberInfoDao;
import service.CommandProcess;

public class MngMemListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberInfoDao md = MemberInfoDao.getInstance();
		
		try {
			int totCnt = md.getTotalCnt();
			String pageNum = request.getParameter("pageNum");	
			if (pageNum==null || pageNum.equals("")) {	
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);	
			
			// 중요!
			String pageSize = request.getParameter("pageSize");
			if(pageSize == null || pageSize.equals(""))
				pageSize = "10";
			
			// Integer.parseInt(pageSize)로 바꿔주기!
			int blockSize = 10;
			int startRow = (currentPage - 1) * Integer.parseInt(pageSize) + 1;
			int endRow   = startRow + Integer.parseInt(pageSize) - 1;
			int startNum = totCnt - startRow + 1;
			
			List<dao.sw.MemberInfo> list = md.list(startRow, endRow);
			
			int pageCnt = (int)Math.ceil((double)totCnt/Integer.parseInt(pageSize));
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize -1;
			if (endPage > pageCnt) endPage = pageCnt;	
			
			HttpSession session=request.getSession();
			session.setAttribute("page", 5);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			request.setAttribute("list", list);
			
			// 페이지 사이즈 넘겨주기!!!
			request.setAttribute("pageSize", pageSize);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "mngMemList.jsp";
	}

}