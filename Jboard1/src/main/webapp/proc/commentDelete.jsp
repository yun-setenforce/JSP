<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	

request.setCharacterEncoding("UTF-8");
String no = request.getParameter("no");
String parent = request.getParameter("parent");


//댓글 삭제 
ArticleDAO.getInstance().deleteComment(no);



response.sendRedirect("/Jboard1/view.jsp?no="+parent);

%>