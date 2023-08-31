<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	

request.setCharacterEncoding("UTF-8");
String no = request.getParameter("no");
String parent = request.getParameter("parent");


//댓글 삭제 
ArticleDAO.getInstance().deleteComment(no);



//댓글 카운트 수정 
ArticleDAO.getInstance().updateArticleForCommentMinus(parent);
response.sendRedirect("/Jboard1/view.jsp?no="+parent);

%>