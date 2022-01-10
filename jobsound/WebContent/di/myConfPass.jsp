<%@page import="dao.di.MemberInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
	System.out.println("=====myConfPass====");
	/* String id = request.getParameter("id");  */
	String id = (String)session.getAttribute("sessionID");
	String passwd = request.getParameter("passwd");
	
	System.out.println("sessionID:::"+id);
	System.out.println("passwd:::"+passwd);
	
	MemberInfoDao md = MemberInfoDao.getInstance();
	int result = md.passwdChk(id, passwd);
	System.out.println("myConfPass result::::"+result);
	if(result==1) {
		%>
			<script>
				location.href="<%=context %>/di/mypageUpdateForm.do";
			</script>
<%
	} else {
		out.println("비밀번호가 틀렸습니다.");
	}
%>
