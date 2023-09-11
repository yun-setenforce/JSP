<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../_header.jsp" %>
<script>
	const success =  new URL(location.href).searchParams.get('success');
	
	if(success == 0){
		alert('회권가입 완료. 로그인 후 이용 부탁드립니다.');
	} else if(success == 100){
		alert('아이디, 비밀번호를 다시 확인하십시오.');
	} else if(success == 101){
		alert('로그인 후 사용해주십시오.');
	} else if(success == 200){
		alert('정상적으로 로그아웃 되었습니다.');
	}else if(success == 300){
		alert('비밀번호가 변경 되었습니다. 다시 로그인 하시기 바랍니다.');
	}
</script>
<div id="user">
	<section class="login">
      <form action="/Farmstory2/user/login.do" method="post">
      	<input type="hidden" name="target" value="${target}"/>
      	<input type="hidden" name="group" value="${group}"/>
      	<input type="hidden" name="cate" value="${cate}"/>
      	<input type="hidden" name="no" value="${no}"/>
          <table border="0">
              <tr>
                  <td><img src="/Farmstory2/images/login_ico_id.png" alt="아이디"></td>
                  <td><input type="text" name="uid" placeholder="아이디 입력"></td>
              </tr>
              <tr>
                  <td><img src="/Farmstory2/images/login_ico_pw.png" alt="비밀번호"></td>
                  <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
              </tr>
          </table>
          <input type="submit" value="로그인" class="btnLogin">
      </form>
      <div>
          <h3>회원 로그인 안내</h3>
          <p>
              아직 회원이 아니시면 회원으로 가입하세요.
          </p>
          <a href="/Farmstory2/user/terms.do">회원가입</a>
      </div>
    </section>
 </div>
<%@include file="./../_footer.jsp" %>