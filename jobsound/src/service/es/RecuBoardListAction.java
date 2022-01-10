package service.es;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.Board;
import dao.es.RecuBoard;
import dao.es.RecuBoardDao;
import dao.es.StdChk;
import service.CommandProcess;

public class RecuBoardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			// Board의 총 갯수
			int totCnt=rbd.getTotalCnt();

			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")) {
				pageNum="1";
			}
			String pageSize=request.getParameter("pageSize");	// 10개씩 보기 받아오기
			if(pageSize==null||pageSize.equals(""))
				pageSize="10";
			
			int currentPage=Integer.parseInt(pageNum);
			int blockSize=10;
			int startRow=(currentPage-1)*Integer.parseInt(pageSize)+1;
			int endRow=startRow+Integer.parseInt(pageSize)-1;
			int startNum=totCnt-startRow+1;
			List<RecuBoard> list=rbd.recuList(startRow, endRow);
			int pageCnt=(int) Math.ceil((double)totCnt/Integer.parseInt(pageSize));
			int StartPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=StartPage+blockSize-1;
	
			if(endPage>pageCnt)
				endPage=pageCnt;
			
			
			HttpSession session=request.getSession();
			session.setAttribute("page", 2);
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
			System.out.println(e.getMessage());
		}
		// 사용자에게 보여줄 View
		return "recuBoardList.jsp";
	}
	
	
	
}
