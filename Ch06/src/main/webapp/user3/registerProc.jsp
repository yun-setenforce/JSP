<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정 
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String uid 	= request.getParameter("uid");
	String name = request.getParameter("name");
	String hp 	= request.getParameter("hp");
	String age 	= request.getParameter("age");
	
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
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user3` values (?,?,?,?)");
		psmt.setString(1, uid);
		psmt.setString(2, name);
		psmt.setString(3, hp);
		psmt.setString(4, age); ///Insert할 때 값만 숫자형 확실하면 문자열('21')/숫자형(21) 상관 없음. setInt 안해도 됨.
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	response.sendRedirect("/Ch06/user3/list.jsp");


%>