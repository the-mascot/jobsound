package service.es;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.es.RecuBoardDao;
import service.CommandProcess;

public class CommUpFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commcontent=null;
		try {
			request.setCharacterEncoding("UTF-8");
			int b_num=Integer.parseInt(request.getParameter("b_num"));
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			commcontent=rbd.commUpdateForm(b_num);
			response.setCharacterEncoding("UTF-8");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return commcontent;
	}

}
