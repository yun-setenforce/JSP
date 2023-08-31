<%@page import="kr.co.jboad1.dto.ArticleDTO"%>
<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@page import="kr.co.jboad1.db.SQL"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	String parent = request.getParameter("parent");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	String regip = request.getRemoteAddr(); 
	
	ArticleDTO dto = new ArticleDTO();
	dto.setParent(parent);
	dto.setContent(content);
	dto.setWriter(writer);
	dto.setRegip(regip);
	
	//댓글 입력 
	ArticleDAO.getInstance().insertComment(dto);
	
	
	//댓글 카운트 수정 
	ArticleDAO.getInstance().updateArticleForCommentPlus(parent);
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent);
	


%>
