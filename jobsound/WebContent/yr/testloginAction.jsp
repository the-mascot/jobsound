<%@page import="dao.yr.MemberInfoDao"%>
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
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	MemberInfoDao mid = MemberInfoDao.getInstance();
	int result = mid.testLogin(id, passwd);
	if(result==1){
		session.setAttribute("sessionID", id);
		//id가 mypageCheck에 안나오면 request에 담으면 됨
		response.sendRedirect("notiList.do");
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