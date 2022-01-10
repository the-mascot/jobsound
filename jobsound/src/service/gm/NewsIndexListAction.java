package service.gm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gm.Board;
import dao.gm.BoardDao;
import service.CommandProcess;

public class NewsIndexListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		System.out.println("index->>>>>>>>>>>>"+id);
		BoardDao bd = BoardDao.getInstance();
		try {
			int totCnt = bd.getTotalCnt();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String pageNum = request.getParameter("pageNum");
            if(pageNum==null||pageNum.equals("")) {
                pageNum="1";
            }
            String pageSize=request.getParameter("pageSize");    // 10개씩 보기 받아오기
            if(pageSize==null||pageSize.equals(""))
                pageSize="10";
            //초기 totCnt 5, currentPage 1
            int currentPage=Integer.parseInt(pageNum);
            int blockSize=10;
            int startRow=(currentPage-1)*Integer.parseInt(pageSize)+1;
            int endRow=startRow+Integer.parseInt(pageSize)-1;
            int startNum=totCnt-startRow+1;
            List<Board> list=bd.indexlist(startRow, endRow);
            int pageCnt=(int) Math.ceil((double)totCnt/Integer.parseInt(pageSize));
            int StartPage=(int)(currentPage-1)/blockSize*blockSize+1;
            int endPage=StartPage+blockSize-1;
            
            if (endPage > pageCnt) endPage = pageCnt; // 
            
            System.out.println("startRow"+startRow);
            System.out.println("endRow"+endRow);
            

			session.setAttribute("page", 3);
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
            
            System.out.println(id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        //사용자에게 보여줄 View
        
        return "newsIndex.jsp";
    }
}