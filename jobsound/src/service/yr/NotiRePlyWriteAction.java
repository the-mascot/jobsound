package service.yr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.yr.MemberInfoDao;
import service.CommandProcess;

public class NotiRePlyWriteAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      HttpSession session = request.getSession();
      String id = (String)session.getAttribute("sessionID");
      /* b_num, ref, re_step, re_level,rePlyContent */
      String b_type ="1";
      int ref=Integer.parseInt(request.getParameter("ref"));
      int re_step=Integer.parseInt(request.getParameter("re_step"));
      String commContent = request.getParameter("rePlyContent");
      System.out.println("두번째꺼 commContent 확인::::"+commContent);
      MemberInfoDao mid = MemberInfoDao.getInstance();
      int maxBnum = mid.getBnumMaxCnt(b_type);
      maxBnum=maxBnum+1;
      int maxReLevel= mid.getRelevelMaxCnt(ref, re_step);
      maxReLevel=maxReLevel+1;
      int result = mid.notiRePlyWrite(b_type, id, maxBnum, ref, re_step, maxReLevel, commContent);
      System.out.println("notiRePlyWrite result::::"+result);
      /*
       * PrintWriter pw = response.getWriter(); pw.println("<html>") -> jsp 안가기때문에 
       */
   
      return null;
   }

}