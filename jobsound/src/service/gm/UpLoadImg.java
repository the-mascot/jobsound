package service.gm;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MyFileRenamePolicy;

import service.CommandProcess;

public class UpLoadImg implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("UpLoadImg Start...");
		String upLoadFilename="";
		String filename="";
		
		request.setCharacterEncoding("utf-8");
		int maxSize=5*1024*1024;	// 5메가 파일
		String realPath=request.getServletContext().getRealPath("uploadImages\\");
		File dir = new File(realPath);
	      if (!dir.exists()) dir.mkdirs();
		MultipartRequest multi=
				new MultipartRequest(request, realPath, maxSize, "utf-8", new MyFileRenamePolicy());
		// 
		Enumeration en=multi.getFileNames();
		
		while(en.hasMoreElements()) {
			// input 태그의 속성이 file인 태그의 name 속성값 : 파라미터 이
			String filename1=(String)en.nextElement();
			// 서버의 저장된 파일 이름
			filename=multi.getFilesystemName(filename1);
			// 전송전 원래의 파일 이름
			String orignal=multi.getOriginalFileName(filename1);
			// 전송된 파일의 내용 타입
			String type=multi.getContentType(filename1);
			// 전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체생성
			File file=multi.getFile(filename1);
			System.out.println("real Path : "+realPath);
			System.out.println("파라미터 이름 : "+filename1);
			System.out.println("실제 파일 이름 : "+orignal);
			System.out.println("저장된 파일 이름 : "+filename);
			System.out.println("파일 타입 : "+type);
			if(file!=null) {
				System.out.println("크기 : "+file.length());
			}
		}
		
		upLoadFilename="..\\uploadImages\\"+filename;
		System.out.println(upLoadFilename);
		
		return upLoadFilename;
	}

}
