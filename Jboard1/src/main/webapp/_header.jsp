<%@page import="kr.co.jboad1.vo.UsersVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//현재 로그인 사용자 가져오기 
	UsersVO sessUser = (UsersVO)session.getAttribute("sessUser");
	if(sessUser == null){
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return;
	}
	%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <link rel="stylesheet" href="css/style.css"/>

</head>
<body>
    <div id="container">

        <header>
            <h3>Board System v1.0</h3>
            <p>
                <%=sessUser.getNick() %>님 반갑습니다.
                <a href="/Jboard1/user/logout.jsp " class="logout">[로그아웃]</a>
            </p>
        </header>
        