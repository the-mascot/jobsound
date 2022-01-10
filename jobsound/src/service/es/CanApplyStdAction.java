package service.es;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.RecuBoardDao;
import service.CommandProcess;

public class CanApplyStdAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session=request.getSession();
			request.setCharacterEncoding("utf-8");
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			String pageNum=request.getParameter("pageNum");
			String pageSize=request.getParameter("pageSize");
			String b_num=request.getParameter("b_num");
			int stdnum=Integer.parseInt(request.getParameter("stdnum"));
			String id=(String)session.getAttribute("sessionID");
			
			int result=rbd.cancleApplyStd(stdnum, id);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("result", result);
			request.setAttribute("b_num", b_num);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "canApplyStdPro.jsp";
	}

}
