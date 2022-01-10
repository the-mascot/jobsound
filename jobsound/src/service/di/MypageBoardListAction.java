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

public class MypageBoardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		String b_type = request.getParameter("b_type");
		System.out.println("id::::"+id);
		System.out.println("b_type::::"+b_type);
		BoardDao bd = BoardDao.getInstance();
		int totCnt = bd.getTotalCnt(id, b_type); // 여기서부터 타입선택으로 가야됨
		System.out.println("bd totCnt::::"+totCnt);
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum::::"+pageNum);
		if(pageNum==null || pageNum.equals("")) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 10, blockSize=10; // 이상하면 blockSzie 10으로 수정
		int startRow=(currentPage -1)* pageSize + 1;
		int endRow=startRow + pageSize -1;
		// 파라미터로 btype 넘겨줌, 리스트 여러개 만들어야됨
		List<Board> list = bd.mypageBoardList(startRow, endRow, id, b_type);
		int startNum = totCnt - startRow + 1;
		int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
		int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
		int endPage = startPage + blockSize -1;
		if(endPage>pageCnt) endPage = pageCnt;
		
		request.setAttribute("id", id);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("startNum", startNum);
		request.setAttribute("list", list);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("cnt", (currentPage -1)*pageSize);
		
		return "mypageBoardList.jsp";
	}

}
