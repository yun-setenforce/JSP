<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@page import="kr.co.jboad1.dto.ArticleDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

//인코딩 설정 
request.setCharacterEncoding("UTF-8");

//전송 데이터 수신
String title 	= request.getParameter("title");
String content = request.getParameter("content");
String file = request.getParameter("file");
String regip = request.getRemoteAddr(); //사용자 아이피 주소 
String no = request.getParameter("no");




//데이터이스 처리 
ArticleDTO dto = new ArticleDTO();

dto.setNo(no);
dto.setTitle(title);
dto.setContent(content);
dto.setRegip(regip);


ArticleDAO.getInstance().updateArticle(dto);

response.sendRedirect("/Jboard1/view.jsp?no="+no);


%>