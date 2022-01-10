package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import service.CommandProcess;


/**
 * Servlet implementation class Controller
 */
// @WebServlet("/Controller")
public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;
    private Map<String, Object> commandMap=new HashMap<String, Object>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    
   /**
    * @see Servlet#init(ServletConfig)
    */
   public void init(ServletConfig config) throws ServletException {
      // web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
      String props=config.getInitParameter("config");
      // 명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
      System.out.println("Controller props->"+props);
      Properties pr=new Properties();
      FileInputStream f=null;
      
      
      try {
         String configFilePath=config.getServletContext().getRealPath(props);
         System.out.println("Contorller configFilePath->"+configFilePath);
         f=new FileInputStream(configFilePath);
         // command.properties 파일의 정보를 Properties객체에 저장
         pr.load(f);
         
      } catch (IOException e) {
         throw new ServletException(e);
      } finally {
         if(f!=null)
            try {
               f.close();
            } catch (IOException ex) {}
      }
      // Iterator 객체는 Enumeration 객체를 확장시킨 개념의 객체
      Iterator keyIter=pr.keySet().iterator();
      // 객체를 하니씩 꺼내서 그 객체명으로 Properties 객체에 저장된 객체에 접근
      while(keyIter.hasNext()) {
         String command=(String)keyIter.next();      // /writeForm.do   /list.do
         String className=pr.getProperty(command);   // service.WriteFormAction service.ListAction
         System.out.println("Controller className->"+className);
         System.out.println("Controller command->"+command);
         // ListAction la=new ListAction();
         
         try {
            Class<?> commandClass=Class.forName(className);   // 해당 문자열을 클래스로 만든다.
            // Object commandInstance=commandClass.newInstance();   // 해당 클래스의 객체를 생성
            Object commandInstance=commandClass.getDeclaredConstructor().newInstance();
            // WriteFormAction wfa=new WriteFormAction();
            commandMap.put(command, commandInstance);   // Map객체인 commandMap에 객체 저장
         } catch (Exception e) {
            throw new ServletException(e);
         }   
      }
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      contRequestPro(request, response);
   }
   
   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("do post 실행..");
      request.setCharacterEncoding("UTF-8");
      contRequestPro(request, response);
   }
   
   private void contRequestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String view=null;
      CommandProcess com=null;
      String command=request.getRequestURI();
      System.out.println("command1=> "+command); //   /ch16/com
      response.setContentType("text/html;charset=utf-8");
      String isBjax = request.getParameter("isBjax");
      if(isBjax == null) {
         isBjax = "0";
      }
      try {
         command=command.substring(request.getContextPath().length());
         com=(CommandProcess) commandMap.get(command);
         System.out.println("command2 substring => "+command);   //   /ch16/com
         System.out.println("com=> "+com);   //   /ch16/com
         
         
         
         if(command.contains("Ajax")) {
            System.out.println("Ajax return 실행..");
            PrintWriter out=response.getWriter();
            view=com.requestPro(request, response);
            System.out.println("view=>"+view);   //   /ch16/com
            out.print(view);
         } else if(isBjax.equals("1")) { 
            com.requestPro(request, response);
         } else {
        	view=com.requestPro(request, response);
        	System.out.println("view=>"+view);   //   /ch16/com
            RequestDispatcher dispatcher=request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
         }
         
      } catch (Throwable e) {
         throw new ServletException(e);
      }
   }

}