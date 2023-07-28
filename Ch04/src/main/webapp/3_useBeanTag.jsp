<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>3_useBeanTag</title>
		<!-- 
			날짜 : 2023/07/26
			이름 : 정채윤
			내용 : JSP useBean 액션태그 실습하기 
			
			userBean 액션태그 
			 - 자바빈을 활용해서 전송 데이터를 수신하고 객체를 생성하는 태그
			 
			자바빈 (JavaBean)
			 - 일반적으로 JSP에서 특정 기능을 담당하는 컴포넌트 클래스
			 - form 또는 table의 필드(속성)를 멤버로 갖는 클래스
			 - MVC 모델, Spring에서 자바빈을 VO(Value Object) 또는 DTO(Data Transfer Object)라고 부름 
		 -->
	</head>
	<body>
		<h3>3.useBean 액션태그</h3>
		
		<h4>사용자 입력</h4>
		<form action="./proc/userProc.jsp" method="get">
			<input type="text" name="uid" placeholder="아이디 입력"/><br>
			<input type="text" name="name" placeholder="이름 입력"/><br>
			<input type="text" name="hp" placeholder="휴대폰 입력"/><br>
			<input type="number" name="age" placeholder="나이 입력"/><br>
			<input type="submit" value="전송하기"/><br>
		</form>
	</body>
</html>