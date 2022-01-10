<%@page import="dao.di.MemberInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String context = request.getContextPath();
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	System.out.println("id:::::"+id);
	System.out.println("passwd:::::"+passwd);
	MemberInfoDao mid = MemberInfoDao.getInstance();
	
	int result = mid.testLogin(id, passwd);
	System.out.println("loginAction result::::"+result);
	if(result==1){
		session.setAttribute("sessionID", id);
		//id가 mypageCheck에 안나오면 request에 담으면 됨
		/* response.sendRedirect("mypageCheck.jsp"); */
		RequestDispatcher rd = request.getRequestDispatcher("mypageCheck.jsp");
		rd.forward(request, response);
	} else if(result ==0){
%>
	<script type="text/javascript">
		alert("암호가 틀렸습니다");
		history.go(-1);
	</script>	
<%	} else { %>
	<script type="text/javascript">
		alert("아이디가 틀렸습니다")
		history.back();
	</script>
<%	}	%>
</body>
</html>