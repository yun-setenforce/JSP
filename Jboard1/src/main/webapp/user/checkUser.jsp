<%@page import="kr.co.jboad1.dao.UserDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String nick = request.getParameter("nick");
	String hp = request.getParameter("hp");
	String email = request.getParameter("email");

	int result = 0;
	
	if(uid!=null){
		result = UserDAO.getInstance().selectCountUid(uid);
	}else if(nick!=null){
		result = UserDAO.getInstance().selectCountNick(nick);
	}else if(email!=null){
		result = UserDAO.getInstance().selectCountEmail(email);
	}else if (hp!=null){
		 result = UserDAO.getInstance().selectCountHp(hp);
	}
	
	//Json 	생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	//Json 출력
	String jsonData = json.toString();
	out.print(jsonData);
%>