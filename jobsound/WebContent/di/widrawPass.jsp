<%@page import="dao.di.MemberInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
	String id = (String)session.getAttribute("sessionID"); 
	String passwd = request.getParameter("passwd");
	System.out.println("widrawPass id:::"+id);
	System.out.println("widrawPass passwd:::"+passwd);
	MemberInfoDao md = MemberInfoDao.getInstance();
	int result = md.passwdChk(id, passwd);
	if(result==1) {
		%>
		<script>
			location.href="../di/widrawMsg.jsp";
		</script>
		<%
	} else {
		out.println("비밀번호가 틀렸습니다.");
	}
%>
