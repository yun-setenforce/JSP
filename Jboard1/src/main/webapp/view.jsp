<%@page import="java.util.List"%>
<%@page import="kr.co.jboad1.dto.ArticleDTO"%>
<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="./_header.jsp" %>
  
<%

      request.setCharacterEncoding("UTF-8");
      String no = request.getParameter("no");
      
      //원글 조회
      ArticleDTO dto = ArticleDAO.getInstance().selectArticle(no);
      
      //댓글 조회  
      List<ArticleDTO  > comments= ArticleDAO.getInstance().selectComments(no);
%>
<script>

//댓글 삭제 
	$(function(){
		
		///댓글 내용 전역변수
		let comment = '';
		
		//댓글 수정 
		$('.mod').click(function(e){
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
		
		//댓글 삭제 
		$('.del').click(function(){
			const result = confirm('정말 삭제 하시겠습니까?');
			return result;
		});
		

		// 댓글쓰기 취소 
		/*
		// Javascript 방식 
		const commentContent = document.querySelector('form > textarea[name=content]');
		const btnCancel = document.querySelector('.btnCancel');
		btnCancel.onclick = function(e){
			e.preventDefault();
			commentContent.value = '';
		}
		*/
		//jQuery 방식
		$('.btnCancel').click(function(e){
			e.preventDefault();
			$('form > textarea[name=content]').val('');
		});
		
		
	});
	
	
	//원글 삭제 

</script>
<main>
    <section id="board" class="view">
        <h3>글보기</h3>
        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" name="title" value="<%=dto.getTitle()%>" readonly/></td>
            </tr>
            <tr>
                <td>첨부파일</td>
                <td>
                    <a href="#">2020년 상반기 매출자료.xls</a>
                    <span>7회 다운로드</span>
                </td>
            </tr>
            <tr>
                <td>내용</td>
                <td>
                    <textarea name="content" readonly><%=dto.getContent()%></textarea>
                </td>
            </tr>
        </table>
        <div>
        <% if(sessUser.getUid().equals(dto.getWriter())){ %>
                    <a href="/Jboard1/delete.jsp?no=<%=dto.getNo() %>" class="btnDelete del">삭제</a>
                    <a href="/Jboard1/modify.jsp?no=<%=dto.getNo() %>" class="btnModify">수정</a>
        <%} %>
                    <a href="/Jboard1/list.jsp" class="btnList">목록</a>
        </div>  
        
        <!-- 댓글리스트 -->
        <section class="commentList">
            <h3>댓글목록</h3>
            <%
            	for(ArticleDTO comment : comments){
            %>
            <article class="comment">
            	<form action="/Jboard1/proc/commentUpdate.jsp" method="post">
            		<input type="hidden" name="no" value="<%=comment.getNo() %>">
            		<input type="hidden" name="parent" value="<%=comment.getParent() %>">
	                <span>
	                    <span><%=comment.getNick() %></span>
	                    <span><%=comment.getRdate() %></span>
	                </span>
	                <textarea name="comment" readonly><%=comment.getContent() %></textarea>
	                <%
	                	if(comment.getWriter().equals(sessUser.getUid())){
	                %>
	                <div><!--  onclick="return confirm('Are you sure you want to delete this ?');"--> 
	                    <a href="/Jboard1/proc/commentDelete.jsp?no=<%=comment.getNo() %>&parent=<%=no %>" class="del">삭제</a>
	                    <a href="#" class="can">취소</a>
	                    <a href="#" class="mod">수정</a>
	                </div>
	                <%
	                	}
	                %>
                </form>
            </article>
            <%
            	}
            %>
            <% if(comments.isEmpty()){ %>
            <p class="empty">등록된 댓글이 없습니다. </p>
            <% } %>
        </section>

        <!-- 댓글입력폼 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="/Jboard1/proc/commentProc.jsp" method="post">
            	<input type="hidden" name="parent" value="<%=no %>"/>
            	<input type="hidden" name="writer" value="<%=sessUser.getUid()	%>"/>
                <textarea name="content"></textarea>
                <div>
                    <a href="#" class="btnCancel">취소</a>
                    <input type="submit" class="btnWrite" value="작성완료"/>
                </div>
            </form>
        </section>
</main>

<%@ include file="./_footer.jsp" %>