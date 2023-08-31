/**
 * 
 */

/** 이메일 인증 **/
$(function(){
	
	let preventDoubleClick = false; //이메일 중복발송을 막기 위한 것 
	
	$('#btnEmailCode').click(function(){
		
		const type = $('input[name=type]').val();
		const uid = $('input[name=uid]').val();
		const name = $('input[name=name]').val();
		const email = $('input[name=email]').val();
		
	/*
		if(!email.match(reEmail)){
			$('.resultEmail').text('유효한 이메일 주소가 아닙니다.');
			isHpOk = false;
			return;
		}
		*/
		
		
		const jsonData = {
				'type' : type,
				'uid' : uid,
				'name' : name,
				'email' : email
		}; //url뒤에 파라미터로 붙여도 되고 json으로 data 보내도 되고 
		
		if(preventDoubleClick){
			return;
		}
		preventDoubleClick = true;
		$('.resultEmail').css('color','inherit').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		$('.resultEmailForId').css('color','inherit').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		$('.resultEmailForPass').css('color','inherit').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		
		setTimeout(function(){
			
			$.ajax({
				url:'/Jboard2/user/authEmail.do',
				type: 'get',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					preventDoubleClick = false;
					if(data.result > 0){
						$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일 입니다.');
						isEmailOk = false;
						

					
						
						if(data.status > 0){
							$('.resultEmail').css('color','inherit').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForId').css('color','inherit').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForPass').css('color','inherit').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('input[name=auth]').attr('disabled', false);
						} else{
							$('.resultEmailForId').css('color','red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
							$('.resultEmailForPass').css('color','red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
						}
						
					} else{
						 if(data.status > 0){
							$('.resultEmail').css('color','inherit').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
						//	$('input[name=auth]').attr('disabled',false);
							$('input[name=auth]').val('');
						} else{
							$('.resultEmail').css('color','red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
							//$('.resultEmail').css('color','inherit').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForId').css('color','red').text('해당하는 사용자 또는 이메일이 존재하지 않습니다.');
							$('.resultEmailForPass').css('color','red').text('해당하는 사용자 또는 이메일이 존재하지 않습니다.');
						
						}
						
					}
					
				}//success end
			});
			
		},1000); //setTimeout end 
	});
	
	$('#btnEmailAuth').click(function(){
		const code =  $('input[name=auth]').val();
		const jsonData = {
				'code': code
		};
		
		$.ajax({
			url : '/Jboard2/user/authEmail.do',
			type : 'POST',
			data : jsonData,
			dataType : 'json',
			success: function(data){
				if(data.result > 0){
					$('.resultEmail').css('color','green').text('이메일 인증이 완료되었습니다.');
					$('.resultEmailForId').css('color','green').text('이메일 인증이 완료되었습니다.');
					$('.resultEmailForPass').css('color','green').text('이메일 인증이 완료되었습니다.');
				//	$('input[name=auth]').attr('disabled','disabled');
					$('input[name=email]').attr('readonly',true);
					isEmailOk = true;
				} else{
					$('.resultEmail').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					$('.resultEmailForId').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					$('.resultEmailForPass').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					isEmailOk = false;
					
				}
				
				
			}
		});
		
		/*
		let code = $('input[name=auth]').val();
		if(receivedCode == code){
			$('.resultEmail').css('color','green').text('이메일이 인증되었습니다.');
			$('input[name=auto]').attr('readonly','readonly');
		}else{
			$('.resultEmail').css('color','red').text('인증에 실패 했습니다.');
		}
		*/
		
	});
});