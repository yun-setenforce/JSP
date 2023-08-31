/*
 *
 */
$(function(){


	/** 아이디 중복 검사 **/
	const inputUid = document.getElementsByName('uid')[0];
	const uidResult = document.getElementsByClassName('uidResult')[0];
	const btnCheckUid = document.getElementById('btnCheckUid');
	
	
	if(btnCheckUid != null) { //myInfo.jsp 에서는 null이라서 
		btnCheckUid.onclick = function(){
			const uid = inputUid.value;
			
			///서버의 부담을 줄이기 위해 ajax처리 전에 유효성을 검사한다.
			if(!uid.match(reUid)){
				uidResult.innerText = '유효한 아이디가 아닙니다.';
				uidResult.style.color = 'red';
				isUidOk = false;
				return;
			}
		
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/Jboard2/user/checkUid.do?uid='+uid); //Model2에서는 jsp 요청못함
			xhr.send();
			
			// 서버 전송
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == XMLHttpRequest.DONE){ //통신완료여부 
					
					if(xhr.status == 200) {//200:요청 성공
					
						const data = JSON.parse(xhr.response);
					
						if(data.result > 0){
							uidResult.innerText = '이미 사용중인 아이디 입니다.';
							uidResult.style.color = 'red';
							isUidOk = false;
						}else{
							uidResult.innerText = '사용 가능한 아이디 입니다.';
							uidResult.style.color = 'green';
							isUidOk = true;	
						}
					}
					
				}//readyState end
				
			}//onreadystatechange end 
		};//onclick end 
	}

	/** 닉네임 중복체크 **/
	$('#btnCheckNick').click(function(){
		const nick = $('input[name=nick]').val();
		
		// 별명 유효성 검사 
		if(!nick.match(reNick)){
			$('.nickResult').css('color', 'red').text('유효한 별명이 아닙니다.');
			isNickOk = false;
			return;
		}
	
		
		$.ajax({
			url: '/Jboard2/user/checkNick.do?nick='+nick,
			type:'get',
			dataType:'json',
			success: function(data){
				if(data.result > 0){
					$('.nickResult').css('color', 'red').text('이미 사용중인 별명입니다');
					isNickOk = false;
				}else{
					$('.nickResult').css('color', 'green').text('사용 가능한 별명입니다');
					isNickOk = true;
				}
			}
		}); //ajax end
		
	});//btnCheckNick click end
	
	/** 휴대폰 중복체크 **/
	$('input[name=hp]').focusout(function(){
		const hp = $(this).val();
		if(!hp.match(reHp)){
			$('.hpResult').css('color', 'red').text('유효한 휴대폰 번호가  아닙니다.');
			isHpOk = false;
			return;
		}
		
		const url = '/Jboard2/user/checkHp.do?hp='+hp;
		
		$.get(url, function(result){
			const data = JSON.parse(result);
			
			if(data.result > 0){
				$('.hpResult').css('color', 'red').text('이미 사용중인 휴대폰입니다.');
				isHpOk = false;
			}else{
				$('.hpResult').css('color', 'green').text('사용 가능한 휴대폰입니다.');
				isHpOk = true;
			}
		});
	});//hp end
}); //onload end 
