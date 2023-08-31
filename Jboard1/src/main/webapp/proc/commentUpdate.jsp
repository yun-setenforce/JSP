<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
String group = request.getParameter("group");
String cate = request.getParameter("cate");
	String parent = request.getParameter("parent");
	String no = request.getParameter("no");
	String comment = request.getParameter("comment");
	
	ArticleDAO.getInstance().updateComment(no, comment);
	
	response.sendRedirect("/Jboard1/view.jsp?group="+group+"&cate="+cate+"&no="+parent);

%>