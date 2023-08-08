<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@page import="kr.co.jboad1.vo.ArticleVO"%>
<%@page import="kr.co.jboad1.vo.UsersVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정 
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String title 	= request.getParameter("title");
	String content = request.getParameter("content");
	String file = request.getParameter("file");
//	String writer = ((UsersVO) session.getAttribute("sessUser")).getUid();
	String writer = request.getParameter("writer");
	String regip = request.getRemoteAddr(); //사용자 아이피 주소 
	
	
	//데이터이스 처리 
	ArticleVO vo = new ArticleVO();
	vo.setTitle(title);
	vo.setContent(content);
	vo.setWriter(writer);
	vo.setRegip(regip);
	
	
	ArticleDAO.getInstance().insertArticle(vo);
	
	response.sendRedirect("/Jboard1/list.jsp");
		
%>