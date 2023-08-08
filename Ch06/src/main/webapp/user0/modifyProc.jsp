<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.User2VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정 
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String uid 	= request.getParameter("uid");
	String name = request.getParameter("name");
	String hp 	= request.getParameter("hp");
	String age 	= request.getParameter("age");
	
	//데이터베이스 처리 
		int result = 0;
	
	try{
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env");
		
		DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		///query문 실행객체
				PreparedStatement psmt = conn.prepareStatement("UPDATE  `user0` SET `name`=?, `hp`=?, `age`=? WHERE `uid`=?");
				psmt.setString(4, uid);
				psmt.setString(1, name);
				psmt.setString(2, hp);
				psmt.setString(3, age); ///Insert할 때 값만 숫자형 확실하면 문자열('21')/숫자형(21) 상관 없음. setInt 안해도 됨.
				result=psmt.executeUpdate();
				psmt.close();
				conn.close();

		
	}catch (Exception e){
		e.printStackTrace();
	}
	//Java JSON 데이터 생성 
	String jsonData = "{\"result\":"+result+"}";
	// JSON 출력 
	out.print(jsonData);
		
%>