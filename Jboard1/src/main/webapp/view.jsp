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
	$(function(){
		$('.del').click(function(){
			const result = confirm('정말 삭제 하시겠습니까?');
			return result;
		});
	});

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
                    <a href="#" class="btnDelete">삭제</a>
                    <a href="#" class="btnModify">수정</a>
                    <a href="/Jboard1/list.jsp" class="btnList">목록</a>
        </div>  
        
        <!-- 댓글리스트 -->
        <section class="commentList">
            <h3>댓글목록</h3>
            <%
            	for(ArticleDTO comment : comments){
            %>
            <article class="comment">
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
                    <a href="#" class="mod">수정</a>
                </div>
                <%
                	}
                %>
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