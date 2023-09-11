<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>


<script>
	$(function(){
		$('.btnDelete').click(function(){
			if(confirm('정말 삭제 하시겠습니까?')){
				return true;	
			}else{
				return false;
			}
		});	
		
		//댓글 삭제 (동적 생성 이벤트 구현)
		$(document).on('click', '.remove', function(e){
			e.preventDefault();

			if(!confirm('정말 삭제 하시겠습니까?')){
				return false;
			}
			
			//alert('클릭!');
			const no = $(this).data('no');
			const article = $(this).parent().parent();//div  > article
			const parent = ${no};
			
			const jsonData = {
					"kind": "REMOVE",
					"no": no,
					"parent": parent
			}
			
			$.ajax({
				url: '/Farmstory2/board/comment.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					if(data.result > 0){
						alert('댓글이  삭제되었습니다.')
						
						//화면처리 
						article.remove();
						
					}
				}
			});
		});
		
		//댓글 작성
		$('#btnComment').click(function(e){
			e.preventDefault();
			
			const cate = $('#formComment > input[name=cate]').val();
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]').val();
			const writer = $('#formComment > input[name=writer]').val();
			const jsonData = {
					"cate" : cate,
					"parent" : parent,
					"content" : content,
					"writer" : writer
			};
			const empty = $('.empty'); //댓글이 없을 경우 댓글이 없습니다<삭제 
			
			console.log('jsonData : '+ jsonData);
			
			$.ajax({
				url: '/Farmstory2/board/comment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					if(data.result > 0){
						alert('댓글이 등록되었습니다.');
						
						// 동적 화면 생성
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2,4);
						const month = dt.getMonth() + 1;
						const date = dt.getDate();
						const now = year + "-" + (("00"+month.toString()).slice(-2))+"-"+(("00"+date.toString()).slice(-2)) ; 
						
						const article = `<article class='comment'>
											<span>
												<span>${sessUser.nick}</span>
												<span>\${now}</span>
											</span>
											<textarea  name="comment" readonly>\${content}</textarea>
											<div>
							                     <a href='#' class='del remove' data-no='\${data.no}'>삭제</a>
							                     <a href='#' class='can'>취소</a>
							                     <a href='#' class='mod modify' data-no='\${data.no}'>수정</a>
							                 </div>
										</article>`;
										
						$('.commentList').append(article);
						$('#formComment > textarea[name=content]').val('');
						empty.remove();
					}else{
						alert('댓글 등록이 실패했습니다.');
					}
				}
			});
		});
		
		

		//댓글 수정 
		// 댓글 수정이 window.onload이후에 a링크 이벤트를 바꾸는 형식이라 $('.클래스').click() 하면안먹음 
		$(document).on('click', '.modify', function(e){
			e.preventDefault();
			const txt = $(this).text();
			const prevContent = $(this).parent().prev().text();
			let isSuccess = false;
			if(txt == '수정'){
				//수정 모드 전환 
				$(this).parent().prev().addClass('modi');
				$(this).parent().prev().attr('readonly', false);
				$(this).parent().prev().focus();
				$(this).text('수정 완료');
				$(this).prev().show();
			}else{
				//수정 완료 클릭 
				
				const result = confirm('정말 수정 하시겠습니까?');
				if(!result){
					return result;
				}
				const content = $(this).parent().prev().val();
				const no = $(this).data('no');
				
				const jsonData = {
						"kind": "MODIFY",
						"no": no,
						"content": content
				};
				const thisBtn = $(this);
				
				//ajax 수정 데이터 전송
				$.ajax({
					url: '/Farmstory2/board/comment.do',
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result > 0){
							isSuccess = true;
							alert('댓글이 수정되었습니다.');
							thisBtn.parent().prev().removeClass('modi');
							thisBtn.parent().prev().attr('readonly', true);
							thisBtn.text('수정');
							thisBtn.prev().hide();
						}else{
							//수정모드 해제 
							isSuccess = false;
							alert('댓글 등록이 실패했습니다.');
							thisBtn.parent().prev().val(prevContent);
							thisBtn.parent().prev().removeClass('modi');
							thisBtn.parent().prev().attr('readonly', true);
							thisBtn.text('수정');
							thisBtn.prev().hide();
						}
					}
				});

				/*
				//수정모드 해제 
				if(!isSuccess){
					alert('실');
					$(this).parent().prev().val(prevContent); //댓글 수정 실패 시 이전에 입력한 댓글로 돌려놓음 
				}
				$(this).parent().prev().removeClass('modi');
				$(this).parent().prev().attr('readonly', true);
				$(this).text('수정');
				$(this).prev().hide();
					*/
					
			}
			
		});
		
		//댓글 수정 취소 
		
		$('.can').click(function(e){
			e.preventDefault();
			$(this).parent().prev().removeClass('modi');
			$(this).parent().prev().attr('readonly', true);
			$(this).hide();                             
			$(this).next().text('수정');
		});
		
		
		

		// 댓글쓰기 취소 
		//jQuery 방식
		$('.btnCancel').click(function(e){
			e.preventDefault();
			$('form > textarea[name=content]').val('');
		});
		
		//파일다운로드 
		$('#downloadFile').click(function(){
			const prevCount = $(this).next().children().text();
			const newCount = parseInt(prevCount) + 1;
			$(this).next().children().text(newCount);
			return true;
			
			/**
			
			e.preventDefault();
			const fno = $(this).data('fno');
			const jsonData = {
					"fno": fno,
			};
			
			$.ajax({
				url: '/Farmstory2/board/fileDownload.do',
				type: 'get',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					if(data.result > 0){
						alert('파일이 다운로드 되었습니다.');
						

					}else{
						alert('파일 다운로드에 실패했습니다.');
					}
				}
			});
			**/
		});
	});
</script>


<section class="view">
    <h3>글보기</h3>
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${article.title}" readonly/></td>
        </tr>
        <tr>
        	<td>조회수</td>
        	<td>${article.hit} 회</td>	
        </tr>
        <c:if test="${article.file > 0}">
        <tr>
            <td>첨부파일</td>
            <td>
                <a href="/Farmstory2/board/fileDownload.do?fno=${article.fileDto.fno}" id="downloadFile" data-fno="${article.fileDto.fno}">${article.fileDto.oriName } &nbsp;</a>
                <span>[ <span>${article.fileDto.download}</span>회 다운로드 ]</span>
            </td>
        </tr>
        </c:if>
        <tr>
            <td>내용</td>
            <td>
                <textarea name="content" readonly>${article.content}</textarea>
            </td>
        </tr>
    </table>
    <div>
    	<c:if test="${sessUser.uid eq article.writer }">
	        <a href="/Farmstory2/board/delete.do?group=${group}&cate=${cate}&no=${no}" class="btnDelete">삭제</a>
	        <a href="/Farmstory2/board/modify.do?group=${group}&cate=${cate}&no=${no}" class="btnModify">수정</a>
        </c:if>
        <a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
    </div>
    
    <!-- 댓글리스트 -->
    <section class="commentList">
        <h3>댓글목록</h3>
        <c:forEach var="comment" items="${comments}">
	        <article class="comment">
        		<input type="hidden" name="no"     value="${comment.no}">
	        	<input type="hidden" name="parent" value="${no}"/>
        		<input type="hidden" name="group" value="${group}">
        		<input type="hidden" name="cate" value="${cate}">
				<span>
					<span>${ comment.getNick() }</span>
					<span>${ comment.getRdate() }</span>
				</span>
				<textarea name="comment" readonly>${ comment.getContent() }</textarea>
             	<c:if test="${sessUser.uid eq comment.writer }">
				<div>
					<a href="#" class="del remove" data-no="${comment.no}">삭제</a>
					<a href="#" class="can">취소</a>
					<a href="#" class="mod modify"  data-no="${comment.no}">수정</a>
				</div>      
				</c:if> 
	        </article>
       	</c:forEach>
        
       <c:if test="${empty comments}">
        	<p class="empty">등록된 댓글이 없습니다.</p>
       </c:if>
    </section>

    <!-- 댓글입력폼 -->
    <section class="commentForm">
        <h3>댓글쓰기</h3>
        <form id="formComment" action="/Farmstory2/board/comment.do" method="post">
        	<input type="hidden" name="cate" value="${cate}"/>
        	<input type="hidden" name="parent" value="${no}"/>
        	<input type="hidden" name="writer" value="${sessUser.uid}"/>
            <textarea name="content" class="content"></textarea>
            <div>
                <a href="#" class="btnCancel">취소</a>
                <input type="submit" id="btnComment" class="btnWrite" value="작성완료"/>
            </div>
        </form>
    </section>
</section>
<!-- 내용 끝 -->
        </article>
    </section>
</div>			