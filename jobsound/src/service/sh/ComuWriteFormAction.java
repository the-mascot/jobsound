package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;


public class ComuWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionID");
			System.out.println(id);
			
			
			request.setAttribute("id", id);
			return "comuWriteForm.jsp";
		
	}

}
