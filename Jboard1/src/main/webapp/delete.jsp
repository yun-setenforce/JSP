<%@page import="kr.co.jboad1.dto.ArticleDTO"%>
<%@page import="kr.co.jboad1.dto.UsersDTO"%>
<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	

request.setCharacterEncoding("UTF-8");
String no = request.getParameter("no");


//내가 쓴 글인지 확인 

	UsersDTO sessUser = (UsersDTO)session.getAttribute("sessUser");
	if(sessUser == null){
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return;
	}
	ArticleDTO dto = ArticleDAO.getInstance().selectArticle(no);
	if(!sessUser.getUid().equals(dto.getWriter())){
	%>
	<script>alert('자신의 글만 삭제할 수 있습니다.')</script>
	<%
		response.sendRedirect("/Jboard1/view.jsp?no="+no);
		return;	
		
	}

//글 삭제 

ArticleDAO.getInstance().deleteArticle(no);

response.sendRedirect("/Jboard1/list.jsp");

%>