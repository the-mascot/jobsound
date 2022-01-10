package service.di;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.StudyRecruit;
import dao.di.StudyRecruitDao;
import service.CommandProcess;

public class StudyRecruitListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		StudyRecruitDao srd = StudyRecruitDao.getInstance();
		int totCnt = srd.stdRecruitCnt(id);
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum::::"+pageNum);
		if(pageNum==null || pageNum.equals("")) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize=10, blockSize=10; // 이상하면 blockSzie 10으로 수정
		int startRow=(currentPage -1)* pageSize + 1;
		int endRow=startRow + pageSize -1;
		int startNum = totCnt - startRow + 1;
		int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
		int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
		int endPage = startPage + blockSize -1;
		if(endPage>pageCnt) endPage = pageCnt;
		List<Integer> myStdRecNumList = srd.myStdRecNumList(startRow, endRow, id);
		List<StudyRecruit> list = srd.studyRecruitList(startRow, endRow, id, myStdRecNumList);
		
		request.setAttribute("id", id);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("list", list);
		request.setAttribute("startNum", startNum);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		return "studyRecruitList.jsp";
	}

}
