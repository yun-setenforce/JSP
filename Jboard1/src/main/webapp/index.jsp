<%@page import="kr.co.jboad1.vo.UsersVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	//현재 사용자 로그인 여부 확인 
	UsersVO sessUser = (UsersVO) session.getAttribute("sessUser");
	
	if(sessUser==null) {
		pageContext.forward("./user/login.jsp");
		return;
	}else{
		///pageContext는 시스템 자원 내에서의 요청이므로 서버 경로 (절대경로 )사용할 수xx 
		pageContext.forward("./list.jsp");
	}
%>