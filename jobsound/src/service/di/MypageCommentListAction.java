package service.di;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.Board;
import dao.di.BoardDao;
import service.CommandProcess;

public class MypageCommentListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		System.out.println("CommentListAction Id:::::"+id);
		BoardDao bd = BoardDao.getInstance();
		int cmtTotCnt = bd.getCmtTotalCnt(id);
		System.out.println("Action cmtTotCnt:::::"+cmtTotCnt);
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || pageNum.equals("")) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 10, blockSize=10; // 이상하면 blockSzie 10으로 수정
		int startRow=(currentPage -1)* pageSize + 1;
		int endRow=startRow + pageSize -1;
		List<Board> list = bd.myCommentList(startRow, endRow, id);
//		System.out.println("commetListActio list::::"+list); 넘어옴
		int startNum = cmtTotCnt - startRow + 1;
		int pageCnt = (int)Math.ceil((double)cmtTotCnt/pageSize);
		int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
		int endPage = startPage + blockSize -1;
		if(endPage>pageCnt) endPage = pageCnt;
		
		request.setAttribute("id", id);
		request.setAttribute("cmtTotCnt", cmtTotCnt);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startNum", startNum);
		request.setAttribute("list", list);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		return "mypageCommentList.jsp";
	}

}
