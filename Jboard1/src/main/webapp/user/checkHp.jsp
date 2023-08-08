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
	
	String hp = request.getParameter("hp");
	int result = UserDAO.getInstance().selectCountHp(hp);
	

	//Json 	생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	//Json 출력
	String jsonData = json.toString();
	out.print(jsonData);
%>