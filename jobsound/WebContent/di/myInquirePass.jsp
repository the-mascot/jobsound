<%@page import="dao.di.MemberInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String context = request.getContextPath(); %>
<%
	String id = request.getParameter("id"); 
	String passwd = request.getParameter("passwd");
	String inqNum = request.getParameter("inqNum");
	/* System.out.println("test id:::"+id);
	System.out.println("test passwd:::"+passwd);
	System.out.println("last inqNum:::"+inqNum); */
	MemberInfoDao md = MemberInfoDao.getInstance();
	int result = md.passwdChk(id, passwd);
	if(result==1) {
		%>
		<script>
			location.href="../di/myInquireDeleteMsg.jsp?inqNum="+<%=inqNum%>;
		</script>
		<%
	} else {
		out.println("비밀번호가 틀렸습니다.");
	}
%>
