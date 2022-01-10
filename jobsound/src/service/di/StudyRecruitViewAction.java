package service.di;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.di.StdApply;
import dao.di.StdApplyDao;
import service.CommandProcess;

public class StudyRecruitViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		int stdNum = Integer.parseInt(request.getParameter("stdNum"));
		int applyPn = Integer.parseInt(request.getParameter("applyPn"));
		int stdPn = Integer.parseInt(request.getParameter("stdPn"));
		String pageNum=request.getParameter("pageNum");
		System.out.println("applyPn::::"+applyPn);
		System.out.println("stdPn::::"+stdPn);
		StdApplyDao sad = StdApplyDao.getInstance();
		List<StdApply> list = sad.getStudyRecruitList(id, stdNum);
		int listChk = list.size();
		int reApplyPn = sad.getApplyPn(stdNum, applyPn);
		
		request.setAttribute("applyPn", reApplyPn);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("stdPn", stdPn);
		request.setAttribute("list", list);
		request.setAttribute("id", id);
		request.setAttribute("stdNum", stdNum);
		request.setAttribute("listChk", listChk);
		
		return "studyRecruitView.jsp";
	}

}
