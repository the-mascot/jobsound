<%@page import="dao.di.InquireDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	InquireDao iqd = InquireDao.getInstance();
	System.out.println("===================selectInquireDelete.jsp===================");
	
	String arr = request.getParameter("arr");
	System.out.println("arr :::: "+ arr);
	
	String[] inqNumsParam = arr.split(",");
	int[] inqNums = null;
	if(inqNumsParam!= null) {
		inqNums = new int[inqNumsParam.length];
		for(int i=0; i<inqNums.length; i++) {
			inqNums[i] = Integer.parseInt(inqNumsParam[i]);
		};
	}
	int result = iqd.selectInquireDelete(inqNums);
	System.out.println("result::::"+result);
	if(result>0){
		out.println("선택한 내용이 삭제되었습니다.");
	} else{
		out.println("삭제되지 않았습니다.");
	}
%>