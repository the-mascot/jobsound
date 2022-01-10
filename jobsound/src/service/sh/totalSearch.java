package service.sh;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.sh.Board;
import dao.sh.BoardDao;
import service.CommandProcess;

public class totalSearch implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
					
				
				try {
					// Board의 총 갯수
					BoardDao bd=BoardDao.getInstance();
					String keyWord=request.getParameter("keyWord");
					int totCnt=bd.gettotSearchCnt(keyWord);
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
					List<Board> list=bd.totSearchList(keyWord, startRow, endRow);
					int pageCnt=(int) Math.ceil((double)totCnt/Integer.parseInt(pageSize));
					int StartPage=(int)(currentPage-1)/blockSize*blockSize+1;
					int endPage=StartPage+blockSize-1;
			
					if(endPage>pageCnt)
						endPage=pageCnt;
					
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
					request.setAttribute("keyWord", keyWord);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				// 사용자에게 보여줄 View
				return "totSearchList.jsp";
	}

}
