<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./_header.jsp" %>
    <script>
		window.onload = function(){
			const chk1 = $('#terms');
			const chk2 = $('.privacy')
			const btnNext = document.querySelector('.btnNext');
			btnNext.addEventListener('click', function(e){
				e.preventDefault();
				if(!chk1.is(':checked')){
					alert('이용약관에 동의하셔야 합니다.');
					return;
				}else if(!chk2.is(':checked')){
					alert('개인정보 취급방침에 동의하셔야 합니다.');
					return;
				}else{
					location.href='/Jboard2/user/register.do';
				}
			});
		}
    </script>
<main id="user">
    <section class="terms">
        <table border="1">
            <caption>사이트 이용약관</caption>
            <tr>
                <td>
                    <textarea name="terms">${requestScope.dto.getTerms()}</textarea>
                    <label><input type="checkbox" id="terms">&nbsp;동의합니다.</label>
                </td>
            </tr>
        </table>

        <table border="1">
            <caption>개인정보 취급방침</caption>
            <tr>
                <td>
                    <textarea name="privacy">${dto.getPrivacy()}</textarea>
                    <label><input type="checkbox" class="privacy">&nbsp;동의합니다.</label>
                </td>
            </tr>
        </table>
        
        <div>
            <a href="/Jboard2/user/login.do" class="btn btnCancel">취소</a>
            <a href="/Jboard2/user/register.do" class="btn btnNext">다음</a>
        </div>

    </section>
</main>
        
<%@include file="./_footer.jsp" %>