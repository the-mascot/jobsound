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

public class BoardUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int sizeLimit = 15 * 1024 * 1024; // 15Mb
		String realPath = request.getServletContext().getRealPath("uploadImages/");
		System.out.println("절대경로" + realPath);
		BoardDao bd = BoardDao.getInstance();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		

		// upload 폴더가 없는 경우 폴더를 만들기
		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		MultipartRequest multpartRequest = null;
		multpartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new MyFileRenamePolicy());
		// 파일 저장명 변경
		String b_num = multpartRequest.getParameter("b_num");
		String content = multpartRequest.getParameter("content");
		String title = multpartRequest.getParameter("title");

		Enumeration<?> files = multpartRequest.getFileNames();
		
		String file = (String) files.nextElement();
		String po_file = multpartRequest.getFilesystemName(file);
		String imagepath = null;
		if (po_file == null) {
			imagepath = null;
		} else {
			imagepath = "..\\uploadImages\\" + po_file;
		}
		System.out.println("이미지 경로::::::::::" + imagepath);
		System.out.println("파일 엘리먼트 명" + file);
		System.out.println("파일 시스템 명:" + po_file);

		System.out.println("받아온 id값" + id);
		System.out.println("받아온 b_num값" + b_num);

		boolean chk = bd.chkWriter(b_num, id);
		System.out.println("수정 완료후 실행된 chk값" + chk);

		if (chk==false){
			//아이디가 다름
			request.setAttribute("chk", chk);
			return "idCheckForm.jsp";
		} else {
			//정상 실행
			
			System.out.println("타이틀 출력"+title);
			int result = bd.boardUpdate(b_num,content,title,imagepath);
			
			request.setAttribute("b_num", b_num);
			request.setAttribute("result", result);
			return "updatePro.jsp";
		}
	}

}
