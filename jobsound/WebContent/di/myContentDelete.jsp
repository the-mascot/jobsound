<%@page import="java.util.Arrays"%>
<%@page import="dao.di.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDao bd = BoardDao.getInstance();
	System.out.println("===================myContentDelete.jsp===================");
	
	String arr = request.getParameter("arr");
	System.out.println("arr :::: "+ arr);
	
	String[] bNumsParam = arr.split(",");
	int[] bNums = null;
	if(bNumsParam!= null) {
		bNums = new int[bNumsParam.length];
		for(int i=0; i<bNums.length; i++) {
			bNums[i] = Integer.parseInt(bNumsParam[i]);
		};
	};
	System.out.println("bNums1:::::"+Arrays.toString(bNums));
	bd.myBoardRefDelete(bNums);
	System.out.println("bNums2:::::"+Arrays.toString(bNums));
	int result = bd.myBoardDelete(bNums);
	System.out.println("result2::::"+result);
	if(result>0){
		out.println("선택한 내용이 삭제되었습니다.");
	} else{
		out.println("삭제되지 않았습니다.");
	}
%>