<%@page import="kr.co.jboad1.vo.UsersVO"%>
<%@page import="kr.co.jboad1.dao.UserDAO"%>
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
	String uid 	= request.getParameter("uid");
	String pass = request.getParameter("pass1");
	String name = request.getParameter("name");
	String nick = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr(); //사용자 아이피 주소 
	
	UsersVO vo = new UsersVO();
	vo.setUid(uid);
	vo.setPass(pass);
	vo.setName(name);
	vo.setNick(nick);
	vo.setEmail(email);
	vo.setHp(hp);
	vo.setZip(zip);
	vo.setAddr1(addr1);
	vo.setAddr2(addr2);
	vo.setRegip(regip);
	
	UserDAO.getInstance().insertUser(vo);
	
	response.sendRedirect("/Jboard1/user/login.jsp");
		
%>