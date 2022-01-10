package service.es;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.es.Board;
import dao.es.RecuBoard;
import dao.es.RecuBoardDao;
import dao.es.StdAppply;
import service.CommandProcess;

public class RecuUpFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			String pageNum=request.getParameter("pageNum");
			String pageSize=request.getParameter("pageSize");	
			int b_num=Integer.parseInt(request.getParameter("b_num"));
			
			RecuBoard board=rbd.recuContent(b_num);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("board", board);
			request.setAttribute("b_num", b_num);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "recuUpdateForm.jsp";
	}

}
