<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="./_header.jsp" %>

<script>
	$(function(){
		$('.btnRemove').click(function(){
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
			const article = $(this).parent().parent();
		
			const jsonData = {
					"kind": "REMOVE",
					"no": no
			}
			
			$.ajax({
				url: '/Jboard2/comment.do',
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
			
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]').val();
			const writer = $('#formComment > input[name=writer]').val();
			const jsonData = {
					"parent" : parent,
					"content" : content,
					"writer" : writer
			};
			const empty = $('.empty');
			
			console.log('jsonData : '+ jsonData);
			
			$.ajax({
				url: '/Jboard2/comment.do',
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
						const now = year + "-" + month + "-" +date ; 
						
						const article = `<article>
											<span class='nick'>${sessUser.nick}</span>
											<span class='date'>\${now}</span>
											<p class='content'>\${content}</p>
											<div>
							                     <a href='#' class='remove'>삭제</a>
							                     <a href='#' class='can'>취소</a>
							                     <a href='#' class='modify'>수정</a>
							                 </div>
										</article>`;
										
						$('.commentList').append(article);

						empty.remove();
					}else{
						alert('댓글 등록이 실패했습니다.');
					}
				}
			});
		});
		
		

		//댓글 수정 
		$('.modify').click(function(e){
			e.preventDefault();
			const txt = $(this).text();
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
				//수정 데이터 전송 
				$(this).closest('form').submit(); //  가장 가까운 폼 태그 선택  
				
				//수정모드 해제 
				$(this).parent().prev().removeClass('modi');
				$(this).parent().prev().attr('readonly', true);
				$(this).text('수정');
				$(this).prev().hide();
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
	});
</script>

 <main id="board">
     <section class="view">
         
         <table border="0">
             <caption>글보기</caption>
             <tr>
                 <th>제목</th>
                 <td><input type="text" name="title" value="${article.title}" readonly/></td>
             </tr>
             <c:if test="${article.file > 0 }">
             <tr>
                 <th>파일</th>
                 <td><a href="/Jboard2/fileDownload.do?fno=${article.fileDto.fno}">${article.fileDto.oriName }</a>&nbsp;
                 <span>${article.fileDto.download }</span>회 다운로드</td>
             </tr>
             </c:if>
             <tr>
                 <th>내용</th>
                 <td>
                     <textarea name="content" readonly>${article.content}</textarea>
                 </td>
             </tr>                    
         </table>
         
         <div>
             <a href="/Jboard2/delete.do?no=${article.no}" class="btn btnRemove">삭제</a>
             <a href="/Jboard2/modify.do?no=${article.no}" class="btn btnModify">수정</a>
             <a href="/Jboard2/list.do" class="btn btnList">목록</a>
         </div>

         <!-- 댓글목록 -->
         <section class="commentList">
             <h3>댓글목록</h3>                   
			
			<c:forEach var="comment" items="${comments}">
             <article>
                 <span class="nick">${comment.nick}</span>
                 <span class="date">${comment.rdate}</span>
                 <textarea class="content">${comment.content}</textarea>  
                    <c:if test="${comment.nick eq sessUser.nick}">                      
                 <div>
                     <a href="#" class="remove" data-no="${comment.no}">삭제</a>
                     <a href="#" class="can">취소</a>
                     <a href="#" class="modify">수정</a>
                 </div></c:if>
             </article>
			</c:forEach>
			<c:if test="${empty comments}">
             	<p class="empty">등록된 댓글이 없습니다.</p>
			</c:if>
			
         </section>

         <!-- 댓글쓰기 -->
         <section class="commentForm">
             <h3>댓글쓰기</h3>
             <form id="formComment" action="/Jboard2/comment.do" method="post">
            	<input type="hidden" name="parent" value="${article.no}"/>
            	<input type="hidden" name="writer" value="${sessUser.uid}"/>
                 <textarea name="content" class="content"></textarea>
                 <div>
                     <a href="#" class="btn btnCancel btnCancel">취소</a>
                     <input type="submit" id="btnComment" value="작성완료" class="btn btnComplete"/>
                 </div>
             </form>
         </section>

     </section>
 </main>

<%@include file="./_footer.jsp" %>