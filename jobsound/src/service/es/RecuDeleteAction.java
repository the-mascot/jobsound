package service.es;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.es.RecuBoardDao;
import service.CommandProcess;

public class RecuDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int re_level=Integer.parseInt(request.getParameter("re_level"));
		try {
			request.setCharacterEncoding("utf-8");
			RecuBoardDao rbd=RecuBoardDao.getInstance();
			String pageNum=request.getParameter("pageNum");
			String pageSize=request.getParameter("pageSize");
			String b_num=request.getParameter("b_num");
			int rdf_b_num=Integer.parseInt(request.getParameter("rdf_b_num"));
			int re_step=Integer.parseInt(request.getParameter("re_step"));
			int ref=Integer.parseInt(request.getParameter("ref"));
			int result=0;
			System.out.println("RecuDelete re_level :"+re_level);
			System.out.println("RecuDelete re_step :"+re_step);
			System.out.println("RecuDelete b_num :"+b_num);
			if(re_level==0)
				result=rbd.recuDelete(rdf_b_num);
			else if(re_level==1)
				result=rbd.commDelete(ref, re_step);
			else
				result=rbd.recommDelete(rdf_b_num);
			
			System.out.println("RecuDeleteAction result : "+result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("result", result);
			request.setAttribute("b_num", b_num);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		if(re_level==0)
			return "recuDelPro.jsp";
		else
			return "recuCommDelPro.jsp";
			
		
	}

}
