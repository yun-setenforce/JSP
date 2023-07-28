<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	///사용자화면 XXX라 html 삭제 
	
	// 인코딩 설정 
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String auto = request.getParameter("auto");
	
	//회원여부 확인 
	if(id.equals("aa") && pw.equals("1234")){
		// 회원일 경우  -> 세션 기록
			
		//자동로그인 처리(자동로그인 체크박스 체크했으면 )
		if(auto != null && auto.equals("1")){
			Cookie autoCookie = new Cookie("cid",id);
			autoCookie.setMaxAge(60*3); //3분 
			response.addCookie(autoCookie);
		}
		
		session.setAttribute("sessid", id);
		response.sendRedirect("./loginSuccess.jsp");
		
		
	}else{
		//회원이 아닐 경우 
		response.sendRedirect("./loginForm.jsp");
	}


%>