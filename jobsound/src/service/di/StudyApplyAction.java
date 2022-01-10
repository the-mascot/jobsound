package service.di;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.di.StdApplyDao;
import service.CommandProcess;

public class StudyApplyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("sessionID");
		String participant_id = request.getParameter("participant_id");
		int stdNum = Integer.parseInt(request.getParameter("stdNum"));
		int apply_chk = Integer.parseInt(request.getParameter("apply_chk"));
		String applyPn=request.getParameter("applyPn");
		String stdPn=request.getParameter("stdPn");
		StdApplyDao sad = StdApplyDao.getInstance();
		int result = sad.studyApply(participant_id, stdNum, apply_chk);
		
		request.setAttribute("applyPn", applyPn);
		request.setAttribute("stdPn", stdPn);
		request.setAttribute("result", result);
		request.setAttribute("stdNum", stdNum);
		request.setAttribute("participant_id", participant_id);
		return "studyApply.jsp";
	}

}
