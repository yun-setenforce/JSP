<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정 
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String seq 	= request.getParameter("seq");
	
	// 데이터베이스 처리 
	///하드코딩. 실제 프로젝트에서는 인증 정보 숨겨야 함 
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	try{
		///드라이버 로드 
		Class.forName("com.mysql.cj.jdbc.Driver");
		///접속 
		Connection conn =  DriverManager.getConnection(host, user, pass);
		///query문 실행객체
		PreparedStatement psmt = conn.prepareStatement("DELETE FROm `user4` WHERE `seq`=?");
		psmt.setString(1, seq);
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	response.sendRedirect("/Ch06/user4/list.jsp");


%>