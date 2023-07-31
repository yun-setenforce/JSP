<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정 
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String uid 	= request.getParameter("uid");
	
	try{
		
		//JNDI 서비스 객체 생성 
		Context initialCtx=new InitialContext();
		Context ctx = (Context) initialCtx.lookup("java:comp/env");
		
		//커넥션 풀에서 커넥션 가져오기 
		DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
		///query문 실행객체
		PreparedStatement psmt = conn.prepareStatement("DELETE FROm `user2` WHERE `uid`=?");
		psmt.setString(1, uid);
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	response.sendRedirect("/Ch06/user2/list.jsp");


%>