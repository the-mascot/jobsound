package service.sh;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MyFileRenamePolicy;

import dao.sh.BoardDao;
import service.CommandProcess;

public class ComuWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int sizeLimit = 15 * 1024 * 1024; // 15Mb
		String realPath = request.getServletContext().getRealPath("uploadImages/");
		System.out.println("절대경로"+realPath);
		
		//upload 폴더가 없는 경우 폴더를 만들기
		File dir = new File(realPath);
		if (!dir.exists()) dir.mkdirs();
				
		MultipartRequest multpartRequest = null;
		multpartRequest = new MultipartRequest(request, 
				realPath, 
				sizeLimit, "utf-8",new MyFileRenamePolicy());
		//파일 저장명 변경
		
		Enumeration<?> files = multpartRequest.getFileNames();
		String file = (String)files.nextElement();
		String po_file = multpartRequest.getFilesystemName(file);
		String imagepath = null;
		if (po_file==null) {
			imagepath = null;
		} else {
			imagepath = "..\\uploadImages\\"+po_file;
		}
		System.out.println("이미지 경로::::::::::"+imagepath);
		System.out.println("파일 엘리먼트 명"+file);
		System.out.println("파일 시스템 명:"+po_file);
		
		HttpSession session = request.getSession();
		BoardDao bd = BoardDao.getInstance();
		
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("sessionID");
		String content = multpartRequest.getParameter("content");
		String title = multpartRequest.getParameter("title");
		
		
		//파일을 업로드 했을때만 이미지 경로 삽입
		
		System.out.println("id:::::::"+id);
		System.out.println("content::::::::"+content);
		System.out.println("title::::::::::"+title);
		System.out.println("imagepath::::::"+imagepath);
		
		
		System.out.println(content);
		int result = bd.comuWrite(id,content,title,imagepath);
		System.out.println("write 프로세스 실행");
		request.setAttribute("result", result);
		return "comuWritePro.jsp";
		
	}

}
