<%@page import="dao.di.StdApplyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("sessionID");
	StdApplyDao sad = StdApplyDao.getInstance();
	System.out.println("===================myStudyApplyCancel.jsp===================");
	
	String arr = request.getParameter("arr");
	System.out.println("arr :::: "+ arr);
	
	String[] stdNumsParam = arr.split(",");
	int[] stdNums = null;
	if(stdNumsParam!= null) {
		stdNums = new int[stdNumsParam.length];
		for(int i=0; i<stdNums.length; i++) {
			stdNums[i] = Integer.parseInt(stdNumsParam[i]);
		};
	}
	int result = sad.myStudyCancel(stdNums, id);
	System.out.println("result::::"+result);
	if(result>0){
		out.println("선택한 스터디가 취소되었습니다.");
	} else{
		out.println("취소되지 않았습니다.");
	}
%>