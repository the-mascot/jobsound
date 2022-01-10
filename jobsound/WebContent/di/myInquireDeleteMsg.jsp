<%@page import="dao.di.InquireDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String context = request.getContextPath(); %>
<%
	String id = (String)session.getAttribute("sessionID");
	System.out.println("deleteAction id:::"+id);
	String paramInqNum = request.getParameter("inqNum");
	System.out.println("inquire delete paramInqNum:::::"+paramInqNum);
	int inqNum=Integer.parseInt(paramInqNum);
	InquireDao iqd = InquireDao.getInstance();
	int result = iqd.inquireDelete(id, inqNum);
	if(result>0){
		%>
		<script>
			alert("문의사항이 정상적으로 삭제되었습니다.");
			location.href="<%=context %>/di/myInquireList.do";
		</script>
		<%
	} else{
		%>
		<script>
			alert("문의사항이 삭제되지 않았습니다.");
			location.href="<%=context %>/di/myInquireList.do";
		</script>
		<%		
	}
%>