/**
 * 
 */

 
$(function(){
	let success =  new URL(location.href).searchParams.get('success');
	
	if(success ==200){
		alert('성공적으로 반영되었습니다.');
	}
	
	//user - role change
	$('.selectRole').change( function(){
		$(this).children().removeAttr('selected');
		$(this).find('option[value='+$(this).val()+']').attr('selected', 'selected');
		
	});
	
	
	//항목  체크박스  
	$('input[name=all]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if(isChecked){
			// 전체선택
			$('input[name=chk]').prop('checked', true);
		}else{
			// 전체해제
			$('input[name=chk]').prop('checked', false);
		}
	});
	
	// 항목 체크박스 - 삭제 
	$('.deleteSelected').click(function(e){
		e.preventDefault();
		
		/*userList - kind : delete 설정 */
		var i = document.createElement("input"); // input 엘리멘트 생성 
			i.setAttribute("type","hidden"); // type 속성을 hidden으로 설정
			i.setAttribute("name","kind"); // name 속성을 'm_nickname'으로 설정 
			i.setAttribute("value","delete"); // value 속성을 neilong에 담겨있는 값으로 설정 
		$('#formCheck').append(i);
		
		$('#formCheck').submit();
	});
	// 항목 체크박스 - 수정 
	$('.updateSelected').click(function(e){
		e.preventDefault();
		
		const trCheckeds = $('input[name=chk]:checked').parent().parent();
		
		let grades = [];
		for(let tr of trCheckeds){
			let gradeSelect = tr.querySelectorAll('select')[0];
			let grade = gradeSelect.options[gradeSelect.selectedIndex].value;
			grades.push(grade);
		}
		for(let grade of grades){
			var input = $("<input>")
               .attr("type", "hidden")
               .attr("name", "role").val(grade);
			$('#formCheck').append(input);
		}

		/*userList - kind : modify 설정 */
		var i = document.createElement("input"); // input 엘리멘트 생성 
			i.setAttribute("type","hidden"); // type 속성을 hidden으로 설정
			i.setAttribute("name","kind"); // name 속성을 'm_nickname'으로 설정 
			i.setAttribute("value","modify"); // value 속성을 neilong에 담겨있는 값으로 설정 
		
		
		$('#formCheck').append(i);
		
		$('#formCheck').submit();
	});


	//order 팝업 
    $('.showOrderPopup').click(function(e){
        e.preventDefault();
        const tr 		 = $(this).parent().parent();
        const orderNo	 = tr.find('.orderNo').text();
        const pName		 = tr.find('.pName').text();
        const price		 = tr.find('.price').text();
        const count		 = tr.find('.count').text();
        const delivery	 = tr.find('.delivery').text();
        const total	 	 = tr.find('.total').text();
        const orderer	 = tr.find('.orderer').text();
        const date 		 = tr.find('.date').text();
       
        const orderProduct 	= tr.find('.orderProduct').text();
        const thumb1 	 = tr.find('.thumb1').text();
        const receiver 	 = tr.find('.receiver').text();
        const address 	 = tr.find('.address').text();
        const etc 		 = tr.find('.etc').text();
        
        const popup = $('#orderPopup');
       // popup.find('.orderNo').text(orderNo);
        popup.find('.orderProduct').text(orderProduct);
        if(thumb1 == 'soldOut'){
        	popup.find('.thumb > img').attr("src", "/Farmstory2/admin/images/soldOut.png");
		} else {
        	popup.find('.thumb > img').attr("src", "/Farmstory2/thumb/"+thumb1);
        }
        popup.find('.thumb > img').attr("alt", pName);
        
        popup.find('.receiver').text(receiver);
        popup.find('.address').text(address);
        popup.find('.etc').text(etc);
        
        popup.find('.pName').text(pName);
        popup.find('.price').text(price);
        popup.find('.count').text(count);
        popup.find('.delivery').text(delivery);
        popup.find('.total').text(total);
        popup.find('.orderer').text(orderer);
        popup.find('.date').text(date);

		popup.show();
    });

    $('#orderPopup .btnClose').click(function(){
        $('#orderPopup').hide();
    });


    $('.showUserPopup').click(function(e){
        e.preventDefault();
        
        const tr 		 = $(this).parent().parent();
        const uid		 = tr.find('.uid').text();
        const name		 = tr.find('.name').text();
        const nick		 = tr.find('.nick').text();
        const email		 = tr.find('.email').text();
        const hp	 	 = tr.find('.hp').text();
        const grade	 	 = tr.find('select[name=grade]').val();
        const regDate	 	= tr.find('.regDate').text();
       
        const role 		 = tr.find('.role').text();
        const zip 		 = tr.find('.zip').text();
        const addr1 	 = tr.find('.addr1').text();
        const addr2 	 = tr.find('.addr2').text();
        const regip 	 = tr.find('.regip').text();
        
        const popup = $('#userPopup');
        
       // popup.find('.orderNo').text(orderNo);
        popup.find('.uid').text(uid);
        popup.find('.name').text(name);
        popup.find('.nick').text(nick);
        popup.find('.email').text(email);
        popup.find('.hp').text(hp);
        popup.find('.grade').text(grade +'등급 ('+role+')');
        
        popup.find('.zip').text(zip);
        popup.find('.addr1').text(addr1);
        popup.find('.addr2').text(addr2);
        popup.find('.regip').text(regip);

        popup.find('.regDate').text(regDate)
		popup.show();
    });


    $('#userPopup .btnClose').click(function(){
        $('#userPopup').hide();
    });

});